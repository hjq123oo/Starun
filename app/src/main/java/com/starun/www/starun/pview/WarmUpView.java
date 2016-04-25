package com.starun.www.starun.pview;

import android.app.Activity;

import com.starun.www.starun.model.data.WarmUpData;

/**
 * Created by hjq on 2016/3/28.
 */
public interface WarmUpView {
    /**
     * 获取实现了WarmUpView接口的Activity
     * @return 一个Activity对象
     */
    public abstract Activity getActivity();

    /**
     * 更新热身描述信息
     * @param progress 热身进度
     * @param warmUpData 热身描述数据
     */
    public abstract void onUpdateWarmUpInfo(int progress,WarmUpData warmUpData);

    /**
     * 更新热身进度
     * @param progress 热身进度
     */
    public abstract void onUpdateWarmUpProgress(int progress);

    /**
     * 热身开始
     * @param maxProgress 进度最大值
     */
    public abstract void onWarmUpStart(int maxProgress);

    /**
     * 热身暂停
     */
    public abstract void onWarmUpPause();

    /**
     * 热身结束
     */
    public abstract void onWarmUpStop();
}
