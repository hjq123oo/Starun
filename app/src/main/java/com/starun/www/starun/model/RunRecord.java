package com.starun.www.starun.model;

import java.util.Date;

/**
 * Created by xiaoxue on 2016/4/10.
 */
public class RunRecord{
    double mile;//公里数
    Date staTime;//开始时间
    Date endTime;//结束时间
    double aveSpeed;//平均速度

    public double getMile() {
        return mile;
    }

    public void setMile(double mile) {
        this.mile = mile;
    }

    public Date getStaTime() {
        return staTime;
    }

    public void setStaTime(Date staTime) {
        this.staTime = staTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getAveSpeed() {
        return aveSpeed;
    }

    public void setAveSpeed(double aveSpeed) {
        this.aveSpeed = aveSpeed;
    }
}