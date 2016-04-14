package com.starun.www.starun.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.starun.www.starun.model.data.WarmUpData;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hjq on 2016/4/13.
 */
public class WarmUpDao {
    SQLiteOpenHelper dbHelper = null;

    public WarmUpDao(Context context){
        dbHelper = new StarunSQLiteOpenHelper(context);
    }


    /**
     * 根据warmUpId获取一条热身描述数据
     * @param warmUpId 需要获取的热身描述数据的id
     * @return WarmUpData对象：包含获取的热身描述数据；null：获取热身描述数据异常
     */
    public WarmUpData getWarmUpData(int warmUpId) {
        String sql = "select * from warm_up where warm_up_id = ?";
        WarmUpData warmUpData = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{warmUpId + ""});
        if(cursor.moveToNext()){
            warmUpData = new WarmUpData();
            warmUpData.setWarmUpId(warmUpId);
            warmUpData.setTitle(cursor.getString(cursor.getColumnIndex(("title"))));
            warmUpData.setGist(cursor.getString(cursor.getColumnIndex("gist")));
            warmUpData.setImgPath(cursor.getString(cursor.getColumnIndex("img_path")));
            warmUpData.setSoundPath(cursor.getString(cursor.getColumnIndex("sound_path")));
        }else{
            warmUpData = null;
        }

        cursor.close();
        db.close();
        return warmUpData;
    }

    /**
     * 获取所有热身描述数据
     * @return List<WarmUpData>对象：包含所有热身描述数据；null：获取热身描述数据异常
     */
    public List<WarmUpData> getWarmUpDatas() {
        String sql = "select * from warm_up";
        List<WarmUpData> warmUpDatas = new ArrayList<WarmUpData>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            WarmUpData warmUpData = new WarmUpData();
            warmUpData.setWarmUpId(cursor.getInt(cursor.getColumnIndex("warm_up_id")));
            warmUpData.setTitle(cursor.getString(cursor.getColumnIndex(("title"))));
            warmUpData.setGist(cursor.getString(cursor.getColumnIndex("gist")));
            warmUpData.setImgPath(cursor.getString(cursor.getColumnIndex("img_path")));
            warmUpData.setSoundPath(cursor.getString(cursor.getColumnIndex("sound_path")));
            warmUpDatas.add(warmUpData);
        }

        cursor.close();
        db.close();
        return warmUpDatas;
    }


}
