package com.starun.www.starun.dao;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hjq on 2016/4/28.
 */
public class RunPlanSharedPreferences {
    private Context context;

    public RunPlanSharedPreferences(Context context){
        this.context = context;

    }


    /**
     * 调用SharedPreferences存取跑步计划进度
     * @param runPlanId 跑步计划进度依据于run_plan数据表，根据表中run_plan_id顺序进行计划，该值为当前进度的run_plan_id
     * @param weekIndex 跑步计划进行到第几周
     * @param lessonIndex 跑步计划进行到该周的第几课
     * @param planPercentage 跑步计划进度百分比
     */
    public void setPlanSchedule(int runPlanId,int weekIndex,int lessonIndex,float planPercentage){
        SharedPreferences sharedPreferences = context.getSharedPreferences("planSchedule",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("runPlanId",runPlanId);
        editor.putInt("weekIndex",weekIndex);
        editor.putInt("lessonIndex",lessonIndex);
        editor.putFloat("planPercentage",planPercentage);
        editor.commit();
    }

    /**
     * 调用SharedPreferences获取跑步计划进度
     * @return Map<String,Object>对象：runPlanId-runPlanId,<String,Integer>,计划进度id;weekIndex-weekIndex,<String,Integer>,第几周;lessonIndex-lessonIndex,<String,Integer>,第几课;planPercentage-planPercentage,<String,Float>，计划进度百分比;
     */
    public Map<String,Object> getPlanSchedule(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("planSchedule", Context.MODE_PRIVATE);
        int runPlanId = sharedPreferences.getInt("runPlanId",1);
        int weekIndex = sharedPreferences.getInt("weekIndex", 0);
        int lessonIndex = sharedPreferences.getInt("lessonIndex", 0);
        float planPercentage = sharedPreferences.getFloat("planPercentage",0);

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("runPlanId",runPlanId);
        map.put("weekIndex",weekIndex);
        map.put("lessonIndex",lessonIndex);
        map.put("planPercentage",planPercentage);

        return map;
    }
}
