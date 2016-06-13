package com.starun.www.starun.server.data;

/**
 * Created by hjq on 2016/6/6.
 */
public class UserNotification{
    private int notification_id;
    private int user_id;
    private int state;
    private int operated;
    private int extra;

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getOperated() {
        return operated;
    }

    public void setOperated(int operated) {
        this.operated = operated;
    }

    public int getExtra() {
        return extra;
    }

    public void setExtra(int extra) {
        this.extra = extra;
    }
}
