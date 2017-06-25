package br.com.willmo.saudebucal.persistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import br.com.willmo.saudebucal.persistent.dao.ContactDao;
import br.com.willmo.saudebucal.persistent.dao.DayDao;
import br.com.willmo.saudebucal.persistent.dao.ReminderDao;
import br.com.willmo.saudebucal.tools.Constants;

/**
 * Created by WillianMuniz on 6/27/2016.
 */
public abstract class GenericDao extends SQLiteOpenHelper {


    private List<GenericDao> tableList;

    protected SQLiteDatabase db;


    public GenericDao(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
//        tableList = new ArrayList<GenericDao>();
//        tableList.add(new ContactDao(null));
//        tableList.add(new DayDao(null));
//        tableList.add(new ReminderDao(null));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactDao.CREATE_SQL);
        db.execSQL(DayDao.SQL_CREATE);
        db.execSQL(ReminderDao.SQL_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //do nothing for now
    }


}

