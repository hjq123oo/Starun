package com.starun.www.starun.model;

import android.content.Context;

import com.starun.www.starun.dao.RunPlanDao;
import com.starun.www.starun.dao.RunPlanSharedPreferences;
import com.starun.www.starun.presenter.RunPlanExecutionPresenter;
import com.starun.www.starun.presenter.RunPlanPresenter;

import java.util.Map;


/**
 * Created by hjq on 2016/4/28.
 */
public class RunPlan {
    private RunPlanDao runPlanDao;
    private RunPlanSharedPreferences runPlanSharedPreferences;

    public RunPlan(Context context){
        runPlanDao = new RunPlanDao(context);
        runPlanSharedPreferences = new RunPlanSharedPreferences(context);
    }

    public void runStart(){
        Map<String,Object> map = runPlanSharedPreferences.getPlanSchedule();
        int runPlanId = (int)map.get("runPlanId");

    }

    public void runPause(){

    }

    public void runStop(){

    }
}
