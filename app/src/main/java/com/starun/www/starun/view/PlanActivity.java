package com.starun.www.starun.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

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


    private ImageButton btnBack;
    private HorizontalListView planHl;
    private LinearLayout normalLl;
    private LinearLayout desc1Rl;
    private TextView week1Tv;
    private TextView rundesc1Tv;
    private LinearLayout desc2Rl;
    private TextView week2Tv;
    private TextView rundesc2Tv;
    private LinearLayout desc3Rl;
    private TextView week3Tv;
    private TextView rundesc3Tv;
    private LinearLayout moreTextLl;
    private TextView moreText1Tv;

    private int displayType = RunPlanPresenter.PRINCIPLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        findView();

        runPlanPresenter = new RunPlanPresenterImpl(this);
        runPlanPresenter.doLoadPrinciple();

        hListView = (HorizontalListView)findViewById(R.id.plan_hl);
        btnBack = (ImageButton)findViewById(R.id.btn_back);

        ArrayList<String> weekStrs = new ArrayList<String>();
        weekStrs.add("原则");
        weekStrs.add("一");
        weekStrs.add("二");
        weekStrs.add("三");
        weekStrs.add("四");
        weekStrs.add("五");
        weekStrs.add("六");
        weekStrs.add("检查");
        weekStrs.add("七");
        weekStrs.add("八");
        weekStrs.add("九");
        weekStrs.add("十");
        weekStrs.add("十一");
        weekStrs.add("十二");
        weekStrs.add("十三");

        hListView.setAdapter(new PlanListAdapter(getApplicationContext(), weekStrs));
        hListView.setOnItemClickListener(new HorizontalListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "click position is " + position);
                if (position == 0) {
                    displayType = RunPlanPresenter.PRINCIPLE;
                    runPlanPresenter.doLoadPrinciple();
                } else if (position > 0 && position < 7) {
                    displayType = RunPlanPresenter.RUN_PLAN;
                    runPlanPresenter.doLoadRunPlan(position);
                } else if (position == 7) {
                    displayType = RunPlanPresenter.CHECK;
                    runPlanPresenter.doLoadCheck();
                } else {
                    displayType = RunPlanPresenter.RUN_PLAN;
                    runPlanPresenter.doLoadRunPlan(position - 1);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onLoadPlanResult(ArrayList<RunPlanData> planDatas) {
        Log.d(TAG, "onLoadPlanResult planDatas size "+planDatas.size());
        Log.d(TAG, "displayType is "+displayType);

        Log.d(TAG,"getLessonOne is "+planDatas.get(0).getLessonOne());
        Log.d(TAG,"getLessonOnePlan is "+planDatas.get(0).getLessonOnePlan());

        if (planDatas!=null){
            switch (displayType){
                case RunPlanPresenter.PRINCIPLE:
                    normalLl.setVisibility(View.INVISIBLE);
                    moreTextLl.setVisibility(View.VISIBLE);
                    moreText1Tv.setText(planDatas.get(0).getDesc());
                    break;
                case RunPlanPresenter.RUN_PLAN:
                    normalLl.setVisibility(View.VISIBLE);
                    moreTextLl.setVisibility(View.INVISIBLE);
                    rundesc1Tv.setText(planDatas.get(0).getLessonOne());
                    rundesc2Tv.setText(planDatas.get(0).getLessonTwo());
                    rundesc3Tv.setText(planDatas.get(0).getLessonThree());
                    break;
                case RunPlanPresenter.CHECK:
                    normalLl.setVisibility(View.INVISIBLE);
                    moreTextLl.setVisibility(View.VISIBLE);
                    moreText1Tv.setText(planDatas.get(0).getDesc());
                default:
                    break;
            }
        }
        else {
            Log.d(TAG, "planDatas is null");
        }
    }

    private void findView(){
        planHl = (HorizontalListView) findViewById(R.id.plan_hl);
        normalLl = (LinearLayout) findViewById(R.id.normal_ll);
        desc1Rl = (LinearLayout) findViewById(R.id.desc1_rl);
        week1Tv = (TextView) findViewById(R.id.week1_tv);
        rundesc1Tv = (TextView) findViewById(R.id.rundesc1_tv);
        desc2Rl = (LinearLayout) findViewById(R.id.desc2_rl);
        week2Tv = (TextView) findViewById(R.id.week2_tv);
        rundesc2Tv = (TextView) findViewById(R.id.rundesc2_tv);
        desc3Rl = (LinearLayout) findViewById(R.id.desc3_rl);
        week3Tv = (TextView) findViewById(R.id.week3_tv);
        rundesc3Tv = (TextView) findViewById(R.id.rundesc3_tv);
        moreTextLl = (LinearLayout) findViewById(R.id.more_text_ll);
        moreText1Tv = (TextView) findViewById(R.id.more_text1_tv);
    }

}
