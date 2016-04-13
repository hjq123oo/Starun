package com.starun.www.starun.model;

import com.starun.www.starun.model.data.WarmUpData;

/**
 * Created by xiaoxue on 2016/4/10.
 */
public class WarmUp extends Exercise{
    private int progress;//当前热身进度
    private WarmUpData warmUpData;//当前热身需用的数据

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public WarmUpData getWarmUpData() {
        return warmUpData;
    }

    public void setWarmUpData(WarmUpData warmUpData) {
        this.warmUpData = warmUpData;
    }
}