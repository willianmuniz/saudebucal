package br.com.willmo.saudebucal.persistent.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.willmo.saudebucal.entity.Day;
import br.com.willmo.saudebucal.entity.DayType;
import br.com.willmo.saudebucal.entity.Reminder;
import br.com.willmo.saudebucal.persistent.GenericDao;
import br.com.willmo.saudebucal.tools.SqliteUtils;

/**
 * Created by @WillianMuniz on 6/27/2016.
 */
public class ReminderDao extends GenericDao {
    public static final String TABLE_NAME = "`reminder`";
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ( `idreminder` INTEGER PRIMARY KEY AUTOINCREMENT, `idday` INTEGER, `datetime` TEXT, `text1` TEXT, `text2` TEXT, `text3` TEXT, `text4` TEXT, `imagepath1` TEXT, `imagepath2` TEXT, `videopath` TEXT, `lastview` TEXT, `beenviewed` TEXT, `name` TEXT, `notification_message` TEXT);";

    public ReminderDao(Context context) {
        super(context);
    }

    public long insert(Reminder r) {
        long result = -1;
        try {
            db = getWritableDatabase();
            String sql = "INSERT INTO " + TABLE_NAME +
                    "(`idday`,`datetime`,`text1`,`text2`,`text3`,`text4`,`imagepath1`," +
                    "`imagepath2`,`videopath`,`lastview`,`beenviewed`,`name`,`notification_message`) VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
            SQLiteStatement stm = db.compileStatement(sql);
            int param = 1;
            stm.bindLong(param++, r.getDay().getId());
            stm.bindString(param++, SqliteUtils.getString(r.getDateTime().toString(SqliteUtils.DATETIME_FORMAT)));
            stm.bindString(param++, SqliteUtils.getString(r.getText1()));
            stm.bindString(param++, SqliteUtils.getString(r.getText2()));
            stm.bindString(param++, SqliteUtils.getString(r.getText3()));
            stm.bindString(param++, SqliteUtils.getString(r.getText4()));
            stm.bindString(param++, SqliteUtils.getString(r.getImagePath1()));
            stm.bindString(param++, SqliteUtils.getString(r.getImagePath2()));
            stm.bindString(param++, SqliteUtils.getString(r.getVideoPath()));
            if (r.getLastView() != null) {
                stm.bindString(param++, SqliteUtils.getString(r.getLastView().toString(SqliteUtils.DATETIME_FORMAT)));
            } else {
                stm.bindNull(param++);
            }
            if (r.getBeenViewed() != null) {
                stm.bindString(param++, SqliteUtils.getString(r.getBeenViewed().toString(SqliteUtils.DATETIME_FORMAT)));
            } else {
                stm.bindNull(param++);
            }
            stm.bindString(param++, SqliteUtils.getString(r.getName()));
            stm.bindString(param++, SqliteUtils.getString(r.getNotificationMessage()));
            result = stm.executeInsert();
            db.close();
        } catch (Exception e) {
            Log.e("ReminderDao", "insert", e);
        }
        return result;
    }

    public List<Reminder> getList(DayType dayType) {
        db = getWritableDatabase();
        List<Reminder> lista = new ArrayList<Reminder>();
        try {
            String sql = "SELECT r.idreminder, r.datetime, r.text1, r.text2, r.text3, r.text4," +
                    " r.imagepath1, r.imagepath2, r.videopath, r.lastview, r.beenviewed, r.name, " +
                    "r.notification_message, d.idday, d.name, d.type, d.day " +
                    "FROM  reminder r " +
                    "INNER JOIN `day` d ON r.idday = d.idday " +
                    "WHERE d.type = ? ORDER BY r.idreminder DESC";
            Cursor c = db.rawQuery(sql, new String[]{dayType.name()});
            if (c.moveToFirst()) {
                do {
                    Reminder r = loadFromCursor(c);

                    lista.add(r);
                } while (c.moveToNext());
            }
            if (c != null && !c.isClosed()) {
                c.close();
            }
            db.close();
        } catch (Exception e) {
            Log.e("ReminderDao", "getList(daytype)", e);
        }
        return lista;
    }

    public Reminder loadFromCursor(Cursor c) {
        Reminder r = new Reminder();
        try {
            int param = 0;

            r.setId(c.getLong(param++));
            r.setDateTime(LocalDateTime.parse(c.getString(param++), SqliteUtils.getDateTimeFormatter()));
            r.setText1(c.getString(param++));
            r.setText2(c.getString(param++));
            r.setText3(c.getString(param++));
            r.setText4(c.getString(param++));
            r.setImagePath1(c.getString(param++));
            r.setImagePath2(c.getString(param++));
            r.setVideoPath(c.getString(param++));
            String dateTmp = c.getString(param++);
            if (dateTmp != null && (dateTmp.length() > 0)) {
                r.setLastView(LocalDateTime.parse(dateTmp, SqliteUtils.getDateTimeFormatter()));
            }
            dateTmp = c.getString(param++);
            if (dateTmp != null && (dateTmp.length() > 0)) {
                r.setBeenViewed(LocalDateTime.parse(dateTmp, SqliteUtils.getDateTimeFormatter()));
            }
            r.setName(c.getString(param++));
            r.setNotificationMessage(c.getString(param++));

            Day d = new Day();
            d.setId(c.getLong(param++));
            d.setName(c.getString(param++));
            d.setType(DayType.valueOf(c.getString(param++)));
            d.setDay(c.getShort(param++));

            r.setDay(d);
        } catch (Exception e) {
            Log.e("ReminderDao", "loadFromCursor", e);
        }
        return r;
    }

}
