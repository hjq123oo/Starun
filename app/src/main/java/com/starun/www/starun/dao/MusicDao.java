package com.starun.www.starun.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.starun.www.starun.model.data.Mp3Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjq on 2016/6/14.
 */
public class MusicDao {

    SQLiteOpenHelper dbHelper = null;

    public MusicDao(Context context){
        dbHelper = new StarunSQLiteOpenHelper(context);
    }


    public boolean addMp3Info(Mp3Info mp3Info){
        String selectSql = "select * from music where url = ?";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectSql, new String[]{mp3Info.getUrl()});

        if(cursor.moveToNext()){
            return false;
        }else{
            String sql = "insert into music(" +
                    "title," +
                    "artist," +
                    "album," +
                    "duration," +
                    "url" +
                    ") " +
                    "values (?,?,?,?,?)";

            db = dbHelper.getWritableDatabase();
            db.execSQL(sql, new Object[]{
                    mp3Info.getTitle(),
                    mp3Info.getArtist(),
                    mp3Info.getAlbum(),
                    mp3Info.getDuration(),
                    mp3Info.getUrl()
            });
            db.close();
        }


        return true;
    }

    public boolean deleteMp3Info(long id) {
        String sql = "delete from music where id = ?";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql, new Object[]{id});
        db.close();
        return true;
    }

    public Mp3Info getMp3Info(long id){
        String sql = "select * from music where id = ?";
        Mp3Info mp3Info = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{id + ""});
        if(cursor.moveToNext()){
            mp3Info = new Mp3Info();
            mp3Info.setId(id);
            mp3Info.setTitle(cursor.getString(cursor.getColumnIndex(("title"))));
            mp3Info.setArtist(cursor.getString(cursor.getColumnIndex(("artist"))));
            mp3Info.setAlbum(cursor.getString(cursor.getColumnIndex(("album"))));
            mp3Info.setDuration(cursor.getLong(cursor.getColumnIndex(("duration"))));
            mp3Info.setUrl(cursor.getString(cursor.getColumnIndex(("url"))));

        }else{
            mp3Info = null;
        }

        cursor.close();
        db.close();
        return mp3Info;
    }


    public List<Mp3Info> getMp3Infos(){
        String sql = "select * from music";
        List<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext()){
            Mp3Info mp3Info = new Mp3Info();
            mp3Info.setId(cursor.getLong(cursor.getColumnIndex(("id"))));
            mp3Info.setTitle(cursor.getString(cursor.getColumnIndex(("title"))));
            mp3Info.setArtist(cursor.getString(cursor.getColumnIndex(("artist"))));
            mp3Info.setAlbum(cursor.getString(cursor.getColumnIndex(("album"))));
            mp3Info.setDuration(cursor.getLong(cursor.getColumnIndex(("duration"))));
            mp3Info.setUrl(cursor.getString(cursor.getColumnIndex(("url"))));
            mp3Infos.add(mp3Info);
        }

        cursor.close();
        db.close();
        return mp3Infos;
    }
}
