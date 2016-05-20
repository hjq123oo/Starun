package com.starun.www.starun.view.customview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * Created by yearsj on 2016/5/20.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    public MyFragmentAdapter(FragmentManager fm,List<Fragment> list){
        super(fm);
        this.list = list;
    }
    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }
}