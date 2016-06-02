package com.starun.www.starun.server.data;

/**
 * Created by hjq on 2016/6/2.
 */
public class Plan {
    private String user_id;         //用户id
    private int plan_id;            //当前执行到第几个阶段id
    private int lesson_index;       //当前执行到第几课
    private double plan_percentage;//计划执行百分比

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public int getLesson_index() {
        return lesson_index;
    }

    public void setLesson_index(int lesson_index) {
        this.lesson_index = lesson_index;
    }

    public double getPlan_percentage() {
        return plan_percentage;
    }

    public void setPlan_percentage(double plan_percentage) {
        this.plan_percentage = plan_percentage;
    }
}
