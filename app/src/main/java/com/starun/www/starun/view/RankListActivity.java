package com.starun.www.starun.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.starun.www.starun.R;
import com.starun.www.starun.view.customview.MyFragmentAdapter;
import com.starun.www.starun.view.fragment.RankDailyFragment;
import com.starun.www.starun.view.fragment.RankPlanFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public class RankListActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private List<Fragment> fragments;
    private Button planList;
    private Button dailyList;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranklist);
        init();
    }

    public void init(){
        mViewPager = (ViewPager)findViewById(R.id.fri_viewpager_rank);
        fragments = new ArrayList<Fragment>();
        fragments.add(RankPlanFragment.newInstance(true));
        fragments.add(RankDailyFragment.newInstance(true));
        mViewPager.setAdapter(new MyFragmentAdapter(this.getSupportFragmentManager(), fragments));
        mViewPager.setCurrentItem(0);
        planList = (Button)findViewById(R.id.plan_btn_list_rank);
        planList.setOnClickListener(new ClickListener(0));
        planList.setBackgroundResource(R.drawable.tab_btn_onclicked);
        dailyList = (Button)findViewById(R.id.daily_btn_list_rank);
        dailyList.setOnClickListener(new ClickListener(1));
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());

        back = (ImageView)findViewById(R.id.ranklist_bank);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RankListActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 内部类----viewpager监听器
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            if(0 == arg0){
                planList.setBackgroundResource(R.drawable.tab_btn_onclicked);
                dailyList.setBackgroundResource(R.drawable.tab_btn_unclick);
            }
            else{
                planList.setBackgroundResource(R.drawable.tab_btn_unclick);
                dailyList.setBackgroundResource(R.drawable.tab_btn_onclicked);
            }
        }
    }

    /**
     * 内部类---点击事件
     */
    public class ClickListener implements View.OnClickListener{
        private int index = 0;
        public ClickListener(int i){
            index = i;
        }
        @Override
        public void onClick(View v){
            mViewPager.setCurrentItem(index);
        }
    }
}
