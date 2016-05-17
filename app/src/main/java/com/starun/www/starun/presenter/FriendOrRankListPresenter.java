package com.starun.www.starun.presenter;

import android.app.Activity;

/**
 * Created by yearsj on 2016/5/17.
 */
public interface FriendOrRankListPresenter {

    /**
     * 获得所有的按照计划排名的好友列表
     * @param user_id
     */
    public void showListForPlan(String user_id);

    /**
     * 获得所有的按照日常跑步排名的好友列表
     * @param user_id
     */
    public void showListForDaily(String user_id);

    /**
     * 获得详细的好友信息
     * @param user_id
     */
    public void showFriendDetail(String user_id);
}
