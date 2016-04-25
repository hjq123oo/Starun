package com.starun.www.starun.pview;

import android.app.Activity;

import com.starun.www.starun.model.WarmUp;

/**
 * Created by hjq on 2016/3/28.
 */
public interface WarmUpView {
    /**
     * 获取实现了WarmUpView接口的Activity
     * @return 一个Activity对象
     */
    public abstract Activity getActivity();

    public abstract void onUpdateWarmUpInfo(WarmUp warmUp);
    public abstract void onUpdateWarmUpProgress(int progress);
    public abstract void onWarmUpStart();
    public abstract void onWarmUpPause();
    public abstract void onWarmUpStop();
}
