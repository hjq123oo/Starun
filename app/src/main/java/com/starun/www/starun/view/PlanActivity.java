package com.starun.www.starun.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.starun.www.starun.R;
import com.starun.www.starun.adapter.PlanListAdapter;
import com.starun.www.starun.model.data.RunPlanData;
import com.starun.www.starun.presenter.RunPlanPresenter;
import com.starun.www.starun.presenter.impl.RunPlanPresenterImpl;
import com.starun.www.starun.pview.PlanView;
import com.starun.www.starun.view.customview.HorizontalListView;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity implements PlanView{
    public static final String TAG = "PlanActivity";
    private HorizontalListView hListView;
    RunPlanPresenter runPlanPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        runPlanPresenter = new RunPlanPresenterImpl(this);
        runPlanPresenter.doLoadRunPlan(0);

        hListView = (HorizontalListView)findViewById(R.id.plan_hl);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("计划");

        ArrayList<RunPlanData> planDatas = new ArrayList<>();
        RunPlanData runPlanData = new RunPlanData();
        runPlanData.setWeekIndex(0);
        planDatas.add(runPlanData);
        runPlanData.setWeekIndex(1);
        planDatas.add(runPlanData);
        runPlanData.setWeekIndex(2);
        planDatas.add(runPlanData);
        runPlanData.setWeekIndex(3);
        planDatas.add(runPlanData);
        runPlanData.setWeekIndex(3);
        planDatas.add(runPlanData);
        runPlanData.setWeekIndex(3);
        planDatas.add(runPlanData);
        runPlanData.setWeekIndex(3);
        planDatas.add(runPlanData);
        runPlanData.setWeekIndex(3);
        planDatas.add(runPlanData);

        hListView.setAdapter(new PlanListAdapter(getApplicationContext(), (ArrayList) planDatas));
    }

    @Override
    public Activity getActivity() {
        return getActivity();
    }

    @Override
    public void onLoadPlanResult(ArrayList<RunPlanData> planDatas) {
        Log.d(TAG, "onLoadPlanResult");
        if (planDatas!=null){
            hListView.setAdapter(new PlanListAdapter(getApplicationContext(), (ArrayList) planDatas));
        }
        else {
            Log.d(TAG, "planDatas is null");
        }
    }
}
