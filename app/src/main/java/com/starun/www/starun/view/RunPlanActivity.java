package com.starun.www.starun.view;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import com.starun.www.starun.R;
import com.starun.www.starun.model.IRunPlanExecution;
import com.starun.www.starun.presenter.RunPlanExecutionPresenter;
import com.starun.www.starun.presenter.impl.RunPlanExecutionPresenterImpl;
import com.starun.www.starun.presenter.impl.RunPresenterImpl;
import com.starun.www.starun.pview.RunPlanExecutionView;

import java.util.ArrayList;
import java.util.List;

public class RunPlanActivity extends FragmentActivity implements RunPlanMainFragment.OnFragmentInteractionListener,
        RunPlanTipFragment.OnFragmentInteractionListener,MusicFragment.OnFragmentInteractionListener,RunPlanExecutionView {
    RunPlanPageAdapter pager = null;
    ArrayList<Fragment> fragments = null;
    ViewPager vp;

    RunPlanMainFragment runPlanMainFragment = null;
    RunPlanTipFragment runPlanTipFragment = null;
    RunPlanExecutionPresenter runPlanExecutionPresenter = null;
    ImageButton mapButton = null;
   // PagerAdapter adapter = null;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_plan);

        runPlanExecutionPresenter = new RunPlanExecutionPresenterImpl(this);


        initFragment();
        initPager();

        mapButton = (ImageButton)findViewById(R.id.map_btn);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_exercise);
       // init();
    }

    public void initFragment(){
        fragments = new ArrayList<>();
        runPlanMainFragment = new RunPlanMainFragment();
        runPlanTipFragment = new RunPlanTipFragment();
        fragments.add(runPlanMainFragment);
        fragments.add(runPlanTipFragment);
    }
    public void initPager(){
        pager = new RunPlanPageAdapter(getSupportFragmentManager(),fragments);
        vp = (ViewPager) findViewById(R.id.run_plan_viewpager);
        vp.setAdapter(pager);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 1){
                    runPlanExecutionPresenter.doLoadTip();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        adapter = new RunPlanPageAdapter(getSupportFragmentManager(),fragments);
//        pager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        runPlanExecutionPresenter.unregisterReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //动态注册广播接收器
        runPlanExecutionPresenter.registerReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //动态注销广播接收器
        runPlanExecutionPresenter.unregisterReceiver();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Chronometer getChronometer() {
        return runPlanMainFragment.getChronometer();
    }

    @Override
    public void onShowTip(IRunPlanExecution iRunPlanExecution) {
        runPlanTipFragment.onUpdateTip(iRunPlanExecution.getSuggestion());

    }

    @Override
    public void onShowTag(String tagTitle,String tagText) {
        alert = null;
        builder = new AlertDialog.Builder(RunPlanActivity.this);
        alert = builder.setTitle(tagTitle)
                .setMessage(tagText)
                .setPositiveButton("我知道了",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        runPlanExecutionPresenter.doTagFinish();
                        runPlanExecutionPresenter.doRunPrepare();
                    }
                }).create();
        alert.show();
    }

    @Override
    public void onShowOptions(List<String> options) {
        alert = null;
        builder = new AlertDialog.Builder(RunPlanActivity.this);
        alert = builder.setTitle("选择运动方式")
                .setItems((String[])options.toArray(),new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                runPlanExecutionPresenter.doRunOptionChose(which);
            }
        }).create();
        alert.show();
    }

    @Override
    public void onRunStart() {
        runPlanMainFragment.onRunStart();
    }

    @Override
    public void onRunPause() {
        runPlanMainFragment.onRunPause();
    }

    @Override
    public void onRunStop() {
        runPlanMainFragment.onRunStop();
    }

    @Override
    public void onUpdateInfo(IRunPlanExecution iRunPlanExecution) {
        runPlanMainFragment.onUpdateInfo(iRunPlanExecution);
    }

    public RunPlanExecutionPresenter getRunPlanExecutionPresenter(){
        return runPlanExecutionPresenter;
    }
}
