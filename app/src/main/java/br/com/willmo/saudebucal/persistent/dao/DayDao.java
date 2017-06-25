package br.com.willmo.saudebucal.persistent.dao;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import br.com.willmo.saudebucal.entity.Day;
import br.com.willmo.saudebucal.entity.Reminder;
import br.com.willmo.saudebucal.persistent.GenericDao;
import br.com.willmo.saudebucal.tools.SqliteUtils;

/**
 * Created by WillianMuniz on 6/27/2016.
 */
public class DayDao extends GenericDao {
    public static final String TABLE_NAME = "`day`";
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ( `idday` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `type` TEXT, `day` INTEGER);";

    public DayDao(Context context) {
        super(context);
    }

    public long insert(Day d) {
        long result = -1;
        try {
            db = getWritableDatabase();
            String sql = "INSERT INTO " + TABLE_NAME + " (`name`,`type`,`day`) VALUES (?,?,?);";
            SQLiteStatement stm = db.compileStatement(sql);
            int param = 1;
            stm.bindString(param++, SqliteUtils.getString(d.getName()));
            stm.bindString(param++, SqliteUtils.getString(d.getType().name()));
            stm.bindLong(param++, d.getDay());
            result = stm.executeInsert();
            db.close();
        } catch (Exception e) {
            Log.e("DayDao", "insert", e);
        }
        return result;
    }

}
