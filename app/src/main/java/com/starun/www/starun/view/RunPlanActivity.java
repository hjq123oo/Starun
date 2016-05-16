package com.starun.www.starun.view;


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
import android.widget.TextView;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.impl.RunPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class RunPlanActivity extends FragmentActivity implements RunPlanMainFragment.OnFragmentInteractionListener,
        RunPlanTipFragment.OnFragmentInteractionListener,MusicFragment.OnFragmentInteractionListener {
    RunPlanPageAdapter pager = null;
    ArrayList<Fragment> fragments = null;
    ViewPager vp;

   // PagerAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_plan);
        initFragment();
        initPager();
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_exercise);
       // init();
    }

    public void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new RunPlanMainFragment());
        fragments.add(new RunPlanTipFragment());
    }
    public void initPager(){
        pager = new RunPlanPageAdapter(getSupportFragmentManager(),fragments);
        vp = (ViewPager) findViewById(R.id.run_plan_viewpager);
        vp.setAdapter(pager);
//        adapter = new RunPlanPageAdapter(getSupportFragmentManager(),fragments);
//        pager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
