package com.starun.www.starun.model;

/**
 * Created by hjq on 2016/5/8.
 */
public class IMain {
    private double kilometer; //总公里数
    private long time; //总时间
    private int weekIndex; //第几周
    private float percentage; //进度百分比

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

    public int getWeekIndex() {
        return weekIndex;
    }

    public void setWeekIndex(int weekIndex) {
        this.weekIndex = weekIndex;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
