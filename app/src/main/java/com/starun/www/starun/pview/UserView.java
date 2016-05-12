package com.starun.www.starun.pview;

import android.app.Activity;

/**
 * Created by yearsj on 2016/4/28.
 */
public interface UserView {
    /**
     * 获取实现了UserView接口的Activity
     * @return 一个Activity对象
     */
    public  Activity getActivity();

    /**
     * 登陆或注册成功
     */
    public void onSuccess(String msg);

    /**
     * 登陆或注册失败
     */
    public void onFailure();
}
