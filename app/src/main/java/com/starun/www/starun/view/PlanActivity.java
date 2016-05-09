package com.starun.www.starun.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

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

        ArrayList<String> weekStrs = new ArrayList<String>();
        weekStrs.add("原则  ");
        weekStrs.add("第一周  ");
        weekStrs.add("第二周  ");
        weekStrs.add("第三周  ");
        weekStrs.add("第四周  ");
        weekStrs.add("第五周  ");
        weekStrs.add("第六周  ");
        weekStrs.add("中断检查  ");
        weekStrs.add("第七周  ");
        weekStrs.add("第八周  ");
        weekStrs.add("第九周  ");
        weekStrs.add("第十周  ");
        weekStrs.add("第十一周  ");
        weekStrs.add("第十二周  ");
        weekStrs.add("第十三周  ");

        hListView.setAdapter(new PlanListAdapter(getApplicationContext(), weekStrs));
        hListView.setOnItemClickListener(new HorizontalListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"click position is "+position);
            }
        });
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onLoadPlanResult(ArrayList<RunPlanData> planDatas) {
        Log.d(TAG, "onLoadPlanResult");
        if (planDatas!=null){
//            hListView.setAdapter(new PlanListAdapter(getApplicationContext(), (ArrayList) planDatas));
        }
        else {
            Log.d(TAG, "planDatas is null");
        }
    }
}
