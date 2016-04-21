package com.starun.www.starun.presenter;

/**
 * Created by hjq on 2016/3/28.
 */
public interface WarmUpPresenter {

    /**
     *热身开始
     */
    public abstract void doWarmUpStart();

    /**
     * 热身暂停
     */
    public abstract void doWarmUpPause();

    /**
     * 热身结束
     */
    public abstract void doWarmUpStop();
}
