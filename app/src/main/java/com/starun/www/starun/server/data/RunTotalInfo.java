package com.starun.www.starun.server.data;

/**
 * Created by yearsj on 2016/5/17.
 */
public class RunTotalInfo {
    private String user_id;         //用户id
    private String total_distance;     //总距离
    private long total_time;            //总时间
    private double plan_percentage;    //计划完成进度

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(String total_distance) {
        this.total_distance = total_distance;
    }

    public long getTotal_time() {
        return total_time;
    }

    public void setTotal_time(long total_time) {
        this.total_time = total_time;
    }

    public double getPlan_percentage() {
        return plan_percentage;
    }

    public void setPlan_percentage(double plan_percentage) {
        this.plan_percentage = plan_percentage;
    }
}
