package br.com.willmo.saudebucal.persistent.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import org.joda.time.LocalDate;

import br.com.willmo.saudebucal.entity.Contact;
import br.com.willmo.saudebucal.persistent.GenericDao;
import br.com.willmo.saudebucal.tools.SqliteUtils;

/**
 * Created by WillianMuniz on 6/27/2016.
 */
public class ContactDao extends GenericDao {
    public static String TABLE_NAME = "`contact`";
    public static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " ( `idcontact` INTEGER PRIMARY KEY AUTOINCREMENT, `phone` TEXT, `name` TEXT, `initial_date` TEXT);";

    public ContactDao(Context context) {
        super(context);
    }

    public long insert(Contact c) {
        long result = -1;
        try {
            db = getWritableDatabase();
            String sql = "INSERT INTO " + TABLE_NAME + "(`phone`,`name`,`initial_date`) VALUES (?,?,?);";
            SQLiteStatement stm = db.compileStatement(sql);
            int param = 1;
            stm.bindString(param++, c.getPhone());
            stm.bindString(param++, c.getName());
            stm.bindString(param++, c.getInitialDate().toString(SqliteUtils.DATE_FORMAT));
            result = stm.executeInsert();
            db.close();
        } catch (Exception e) {
            Log.e("ContactDao", "insert", e);
        }
        return result;
    }

    public Contact getContact() {
        db = getWritableDatabase();
        Contact result = null;
        try {
            String sql = "SELECT c.idcontact, c.initial_date, c.name, c.phone FROM contact c ORDER BY c.idcontact DESC";
            Cursor c = db.rawQuery(sql, new String[]{});
            if (c.moveToFirst()) {
                do {
                    result = loadFromCursor(c);
                } while (c.moveToNext());
            }
            if (c != null && !c.isClosed()) {
                c.close();
            }
            db.close();
        } catch (Exception e) {
            Log.e("ContactDao", "getContact", e);
        }
        return result;
    }

    /**
     * Preenche um objeto a partir do cursor
     *
     * @param c
     * @return
     */
    public Contact loadFromCursor(Cursor c) {
        Contact r = new Contact();
        try {
            int param = 0;
            r.setId(c.getLong(param++));
            r.setInitialDate(LocalDate.parse(c.getString(param++), SqliteUtils.getDateFormatter()));
            r.setName(c.getString(param++));
            r.setPhone(c.getString(param++));
        } catch (Exception e) {
            Log.e("ContactDao", "loadFromCursor", e);
        }
        return r;
    }
}
