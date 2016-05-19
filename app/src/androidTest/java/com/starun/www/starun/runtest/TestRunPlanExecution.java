package com.starun.www.starun.runtest;

import android.test.AndroidTestCase;

import com.starun.www.starun.dao.RunPlanSharedPreferences;

/**
 * Created by hjq on 2016/5/19.
 */
public class TestRunPlanExecution extends AndroidTestCase {
    public void setRunPlanSharedPreferences(){
        RunPlanSharedPreferences runPlanSharedPreferences = new RunPlanSharedPreferences(getContext());
        runPlanSharedPreferences.setPlanSchedule(8,1,(float)0.4615);
    }
}
