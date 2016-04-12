package com.starun.www.starun.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hjq on 2016/4/6.
 */
public class StarunSQLiteOpenHelper extends SQLiteOpenHelper {
    public StarunSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public StarunSQLiteOpenHelper(Context context){
        super(context, "starun.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"warm_up\" (\n" +
                "\"warm_up_id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\"title\"  TEXT,\n" +
                "\"gist\"  TEXT,\n" +
                "\"img_path\"  TEXT,\n" +
                "\"sound_path\"  TEXT\n" +
                ");");

        db.execSQL("CREATE TABLE \"run_plan\" (\n" +
                "\"run_plan_id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\"title\"  TEXT,\n" +
                "\"plan\"  TEXT,\n" +
                "\"suggestion\"  TEXT,\n" +
                "\"sound_path\"  TEXT\n" +
                ");");

        db.execSQL("CREATE TABLE \"run_record\" (\n" +
                "\"run_record_id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\"start_time\"  INTEGER,\n" +
                "\"end_time\"  INTEGER,\n" +
                "\"kilometer\"  REAL,\n" +
                "\"avg_speed\"  REAL,\n" +
                "\"trace_entity\"  TEXT,\n" +
                "\"usr_id\"  INTEGER\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

