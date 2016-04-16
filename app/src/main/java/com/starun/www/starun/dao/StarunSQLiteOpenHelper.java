package com.starun.www.starun.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.starun.www.starun.R;

/**
 * Created by hjq on 2016/4/6.
 */
public class StarunSQLiteOpenHelper extends SQLiteOpenHelper {


    public StarunSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public StarunSQLiteOpenHelper(Context context) {
        super(context, "starun.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"warm_up\" (\n" +
                "\"warm_up_id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\"title\"  TEXT,\n" +
                "\"gist\"  TEXT,\n" +
                "\"img_id\"  INTEGER,\n" +
                "\"sound_id\"  INTEGER\n" +
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

        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题1", "要领1", R.drawable.warm_up_1, null
                });

        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题2", "要领2", R.drawable.warm_up_2, null
                });


        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题3", "要领3", R.drawable.warm_up_3, null
                });


        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题4", "要领4", R.drawable.warm_up_4, null
                });


        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题5", "要领5", R.drawable.warm_up_5, null
                });


        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题6", "要领6", R.drawable.warm_up_6, null
                });


        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题7", "要领7", R.drawable.warm_up_7, null
                });


        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题8", "要领8", R.drawable.warm_up_8, null
                });

        db.execSQL("INSERT INTO warm_up(" +
                        "title," +
                        "gist," +
                        "img_id," +
                        "sound_id" +
                        ") " +
                        "VALUES (?,?,?,?)",
                new Object[]{
                        "标题9", "要领9", R.drawable.warm_up_9, null
                });
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

