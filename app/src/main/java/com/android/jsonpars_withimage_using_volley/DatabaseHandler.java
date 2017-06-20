package com.android.jsonpars_withimage_using_volley;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by peacock on 3/31/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Forbslist";
    private static final String TABLEMST = "Billionior";
    private static final String KEY_ID = "Id";
    private static final String KEY_NAME = "Prson";
    // private static final String KEY_GENDER = "Gender";
    private static final String KEY_WORTH = "Worth";
    private static final String KEY_YEAR = "Year";
    private static final String KEY_SOURCE = "Source";
    private static final String KEY_IMAGE = "ImageUrl";

    SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void open() {
        db = this.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE = "CREATE TABLE " + TABLEMST + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_WORTH + " TEXT,"
                + KEY_SOURCE + " TEXT,"
                + KEY_YEAR + " INTEGER,"
                + KEY_IMAGE + " TEXT" + ")";
        db.execSQL(DATABASE_CREATE);
    }

    public void Adduser(Forbsbillenior billenior) {
        open();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, billenior.getName());
            values.put(KEY_WORTH, billenior.getWorth());
            values.put(KEY_SOURCE, billenior.getSources());
            values.put(KEY_YEAR, billenior.getYear());
            values.put(KEY_IMAGE, billenior.getPhoto());
            db.insert(TABLEMST, null, values);
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Forbsbillenior> getbillionior() {
        ArrayList<Forbsbillenior> forbsperson = new ArrayList<Forbsbillenior>();
        open();
        try {
            String query = ("SELECT * FROm " + TABLEMST);
            Cursor c = db.rawQuery(query, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        Forbsbillenior forbs = new Forbsbillenior();
                        forbs.setId(c.getString(c.getColumnIndex(KEY_ID)));
                        forbs.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                        forbs.setWorth(c.getString(c.getColumnIndex(KEY_WORTH)));
                        forbs.setSources(c.getString(c.getColumnIndex(KEY_SOURCE)));
                        forbs.setYear(c.getInt(c.getColumnIndex(KEY_YEAR)));
                        forbs.setPhoto(c.getString(c.getColumnIndex(KEY_IMAGE)));
                        forbsperson.add(forbs);
                    } while (c.moveToNext());
                }
                c.close();
            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return forbsperson;
    }

    public int getCount() {
        int num = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String QUERY = "SELECT * FROM " + TABLEMST;
            Cursor cursor = db.rawQuery(QUERY, null);
            num = cursor.getCount();
            db.close();

        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return num;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLEMST);
        onCreate(db);
    }
}
