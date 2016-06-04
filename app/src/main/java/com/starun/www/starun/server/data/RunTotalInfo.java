package com.starun.www.starun.server.data;

/**
 * Created by yearsj on 2016/5/17.
 */
public class RunTotalInfo {
    private int user_id;         //用户id
    private String headImgPath;     //用户头像
    private String nickname;        //用户昵称
    private double total_distance;     //总距离
    private long total_time;            //总时间
    private double plan_percentage;    //计划完成进度

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getHeadImgPath() {
        return headImgPath;
    }

    public void setHeadImgPath(String headImgPath) {
        this.headImgPath = headImgPath;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(double total_distance) {
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
