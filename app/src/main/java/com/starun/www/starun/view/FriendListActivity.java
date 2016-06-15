package com.starun.www.starun.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.starun.www.starun.R;
import com.starun.www.starun.view.customview.MyFragmentAdapter;
import com.starun.www.starun.view.fragment.DailyFragment;
import com.starun.www.starun.view.fragment.PlanFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yearsj on 2016/5/20.
 */
public class FriendListActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private List<Fragment>  fragments;
    private Button planList;
    private Button dailyList;
    private ImageView more;
    private ImageView addFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);
        init();
    }

    public void init(){
        mViewPager = (ViewPager)findViewById(R.id.fri_viewpager);
        fragments = new ArrayList<Fragment>();
        fragments.add(PlanFragment.newInstance(false));
        fragments.add(DailyFragment.newInstance(false));
        mViewPager.setAdapter(new MyFragmentAdapter(this.getSupportFragmentManager(), fragments));
        mViewPager.setCurrentItem(0);
        planList = (Button)findViewById(R.id.plan_btn_list);
        planList.setOnClickListener(new ClickListener(0));
        planList.setBackgroundResource(R.drawable.tab_btn_onclicked);
        dailyList = (Button)findViewById(R.id.daily_btn_list);
        dailyList.setOnClickListener(new ClickListener(1));
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());

        more = (ImageView)findViewById(R.id.more);
        addFriend = (ImageView)findViewById(R.id.add_friend);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FriendListActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入添加好友的界面

                Intent i = new Intent(FriendListActivity.this,SearchFriendActivity.class);
                startActivity(i);

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
