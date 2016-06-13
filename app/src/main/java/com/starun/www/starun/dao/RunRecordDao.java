package com.starun.www.starun.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.starun.www.starun.model.data.RunRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjq on 2016/4/12.
 */
public class RunRecordDao {

    SQLiteOpenHelper dbHelper = null;

    public RunRecordDao(Context context) {
        dbHelper = new StarunSQLiteOpenHelper(context);
    }

    /**
     * 添加一条跑步记录
     * @param runRecord RunRecord对象，包含需要添加的跑步记录
     * @return true：添加记录成功；false：添加记录异常
     */
    public boolean addRunRecord(RunRecord runRecord) {
        String sql = "insert into run_record(" +
                "start_time," +
                "end_time," +
                "run_time" +
                "kilometer," +
                "trace_entity" +
                ") " +
                "values (?,?,?,?,?)";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql, new Object[]{
                runRecord.getStartTime(),
                runRecord.getEndTime(),
                runRecord.getRunTime(),
                runRecord.getKilometer(),
                runRecord.getTraceEntity()
        });
        db.close();
        return true;
    }

    /**
     * 更新一条跑步记录
     * @param runRecord RunRecord对象，包含需要更新的跑步记录
     * @return true：更新记录成功；false：更新记录异常
     */
    public boolean updateRunRecord(RunRecord runRecord) {
        String sql = "update run_record set " +
                "start_time = ?," +
                "end_time = ?," +
                "run_time = ?," +
                "kilometer = ?," +
                "trace_entity = ?," +
                "where run_record_id = ?";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql, new Object[]{
                runRecord.getStartTime(),
                runRecord.getEndTime(),
                runRecord.getRunTime(),
                runRecord.getKilometer(),
                runRecord.getTraceEntity(),
                runRecord.getRunRecordId()
        });
        db.close();
        return true;
    }

    /**
     * 根据runRecordId获取一条跑步记录
     * @param runRecordId 需要获取的跑步记录的id
     * @return RunRecord对象：包含获取的跑步记录；null：获取跑步记录异常
     */
    public RunRecord getRunRecord(int runRecordId) {
        String sql = "select * from run_record where run_record_id = ?";
        RunRecord runRecord = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{runRecordId + ""});
        if(cursor.moveToNext()){
            runRecord = new RunRecord();
            runRecord.setRunRecordId(runRecordId);
            runRecord.setStartTime(cursor.getLong(cursor.getColumnIndex("start_time")));
            runRecord.setEndTime(cursor.getLong(cursor.getColumnIndex("end_time")));
            runRecord.setRunTime(cursor.getLong(cursor.getColumnIndex("run_time")));
            runRecord.setKilometer(cursor.getDouble(cursor.getColumnIndex("kilometer")));
            runRecord.setTraceEntity(cursor.getString(cursor.getColumnIndex("trace_entity")));
        }else{
            runRecord = null;
        }

        cursor.close();
        db.close();
        return runRecord;
    }

    /**
     * 获取用户的所有跑步记录
     * @return List<RunRecord>对象：包含所有跑步记录；null：获取跑步记录异常
     */
    public List<RunRecord> getRunRecords() {
        String sql = "select * from run_record";
        List<RunRecord> runRecords = new ArrayList<RunRecord>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            RunRecord runRecord = new RunRecord();
            runRecord.setRunRecordId(cursor.getInt(cursor.getColumnIndex("run_record_id")));
            runRecord.setStartTime(cursor.getLong(cursor.getColumnIndex("start_time")));
            runRecord.setEndTime(cursor.getLong(cursor.getColumnIndex("end_time")));
            runRecord.setRunTime(cursor.getLong(cursor.getColumnIndex("run_time")));
            runRecord.setKilometer(cursor.getDouble(cursor.getColumnIndex("kilometer")));
            runRecord.setTraceEntity(cursor.getString(cursor.getColumnIndex("trace_entity")));
            runRecords.add(runRecord);
        }

        cursor.close();
        db.close();
        return runRecords;
    }

    /**
     * 根据runRecordId删除一条跑步记录
     * @param runRecordId 需要删除的跑步记录的id
     * @return true：删除记录成功；false：删除记录异常
     */
    public boolean deleteRunRecord(int runRecordId) {
        String sql = "delete from run_record where run_record_id = ?";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql,new Object[]{runRecordId});
        db.close();
        return true;
    }


}
