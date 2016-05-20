package com.starun.www.starun.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.starun.www.starun.R;
import com.starun.www.starun.view.customview.MyFragmentAdapter;

import java.util.List;

/**
 * Created by yearsj on 2016/5/20.
 */
public class FriendListActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private List<Fragment>  fragments;
    private Button planList;
    private Button dailyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);
        init();
    }

    public void init(){
        mViewPager = (ViewPager)findViewById(R.id.fri_viewpager);
        mViewPager.setAdapter(new MyFragmentAdapter(this.getSupportFragmentManager(), fragments));
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        planList = (Button)findViewById(R.id.plan_btn_list);
        planList.setOnClickListener(new ClickListener(0));
        dailyList = (Button)findViewById(R.id.daily_btn_list);
        dailyList.setOnClickListener(new ClickListener(1));
    }

    /**
     * 内部类----viewpager监听器
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
       // private int one = offset*2 +bmpW;//两个相邻页面的偏移量
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
//            Animation animation = new TranslateAnimation(1000,100,0,0);//平移动画
//            //currIndex = arg0;
//            animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
//            animation.setDuration(200);//动画持续时间0.2秒
//            image.startAnimation(animation);//是用ImageView来显示动画的
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
