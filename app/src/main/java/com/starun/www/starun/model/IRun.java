package com.starun.www.starun.model;

import com.starun.www.starun.model.data.RunPlanData;

/**
 * Created by xiaoxue on 2016/4/10.
 */
public class IRun extends Exercise{
    private double kilometer;//已跑的公里数
    private long time;//已用的时间

    public double getKilometer() {
        return kilometer;
    }

    public void setKilometer(double kilometer) {
        this.kilometer = kilometer;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}