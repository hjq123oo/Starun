package com.starun.www.starun.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.starun.www.starun.model.data.RunPlanData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjq on 2016/4/28.
 */
public class RunPlanDao {
    SQLiteOpenHelper dbHelper = null;

    public RunPlanDao(Context context){
        dbHelper = new StarunSQLiteOpenHelper(context);
    }

    /**
     * 根据runPlanId获取一条跑步计划
     * @param runPlanId 需要获取的跑步计划的id
     * @return RunPlanData对象：包含获取的跑步计划；null：获取跑步计划异常
     */
    public RunPlanData getRunPlanData(int runPlanId){
        String sql = "select * from run_plan where run_plan_id = ?";
        RunPlanData runPlanData = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, new String[]{runPlanId + ""});

        if(cursor.moveToNext()){
            runPlanData = new RunPlanData();
            runPlanData.setRunPlanId(runPlanId);
            runPlanData.setTitle(cursor.getString(cursor.getColumnIndex(("title"))));
            runPlanData.setWeekIndex(cursor.getInt(cursor.getColumnIndex("week_index")));
            runPlanData.setOptionIndex(cursor.getInt(cursor.getColumnIndex("option_index")));
            runPlanData.setOptionName(cursor.getString(cursor.getColumnIndex("option_name")));
            runPlanData.setLessonOne(cursor.getString(cursor.getColumnIndex("lesson_one")));
            runPlanData.setLessonOnePlan(cursor.getString(cursor.getColumnIndex("lesson_one_plan")));
            runPlanData.setLessonTwo(cursor.getString(cursor.getColumnIndex("lesson_two")));
            runPlanData.setLessonTwoPlan(cursor.getString(cursor.getColumnIndex("lesson_two_plan")));
            runPlanData.setLessonThree(cursor.getString(cursor.getColumnIndex("lesson_three")));
            runPlanData.setLessonThreePlan(cursor.getString(cursor.getColumnIndex("lesson_three_plan")));
            runPlanData.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
        }else{
            runPlanData = null;
        }

        cursor.close();
        db.close();
        return runPlanData;
    }


    /**
     * 获取第几周的跑步计划
     * @param weekIndex 第几周
     * @return 一个List<RunPlanData>对象，包含跑步计划列表
     */
    public List<RunPlanData> getRunPlanDatasByWeek(int weekIndex){
        String sql = "select * from run_plan where week_index = ?";


        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, new String[]{weekIndex + ""});

        List<RunPlanData> list = new ArrayList<RunPlanData>();

        while(cursor.moveToNext()) {
            RunPlanData runPlanData = new RunPlanData();
            runPlanData.setRunPlanId(cursor.getInt(cursor.getColumnIndex("run_plan_id")));
            runPlanData.setTitle(cursor.getString(cursor.getColumnIndex(("title"))));
            runPlanData.setWeekIndex(cursor.getInt(cursor.getColumnIndex("week_index")));
            runPlanData.setOptionIndex(cursor.getInt(cursor.getColumnIndex("option_index")));
            runPlanData.setOptionName(cursor.getString(cursor.getColumnIndex("option_name")));
            runPlanData.setLessonOne(cursor.getString(cursor.getColumnIndex("lesson_one")));
            runPlanData.setLessonOnePlan(cursor.getString(cursor.getColumnIndex("lesson_one_plan")));
            runPlanData.setLessonTwo(cursor.getString(cursor.getColumnIndex("lesson_two")));
            runPlanData.setLessonTwoPlan(cursor.getString(cursor.getColumnIndex("lesson_two_plan")));
            runPlanData.setLessonThree(cursor.getString(cursor.getColumnIndex("lesson_three")));
            runPlanData.setLessonThreePlan(cursor.getString(cursor.getColumnIndex("lesson_three_plan")));
            runPlanData.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
            list.add(runPlanData);
        }

        cursor.close();
        db.close();
        return list;
    }


    public RunPlanData getRunPlanDataByTag(int tagIndex){
        String sql = "select * from run_plan where tag_index = ?";
        RunPlanData runPlanData = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, new String[]{tagIndex + ""});

        if(cursor.moveToNext()){
            runPlanData = new RunPlanData();
            runPlanData.setRunPlanId(cursor.getInt(cursor.getColumnIndex("run_plan_id")));
            runPlanData.setTitle(cursor.getString(cursor.getColumnIndex(("title"))));
            runPlanData.setWeekIndex(cursor.getInt(cursor.getColumnIndex("week_index")));
            runPlanData.setOptionIndex(cursor.getInt(cursor.getColumnIndex("option_index")));
            runPlanData.setOptionName(cursor.getString(cursor.getColumnIndex("option_name")));
            runPlanData.setLessonOne(cursor.getString(cursor.getColumnIndex("lesson_one")));
            runPlanData.setLessonOnePlan(cursor.getString(cursor.getColumnIndex("lesson_one_plan")));
            runPlanData.setLessonTwo(cursor.getString(cursor.getColumnIndex("lesson_two")));
            runPlanData.setLessonTwoPlan(cursor.getString(cursor.getColumnIndex("lesson_two_plan")));
            runPlanData.setLessonThree(cursor.getString(cursor.getColumnIndex("lesson_three")));
            runPlanData.setLessonThreePlan(cursor.getString(cursor.getColumnIndex("lesson_three_plan")));
            runPlanData.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
        }else{
            runPlanData = null;
        }

        cursor.close();
        db.close();
        return runPlanData;
    }
}
