package com.starun.www.starun.pview;

import android.app.Activity;

import com.starun.www.starun.server.data.User;

import java.util.List;

/**
 * Created by yearsj on 2016/5/17.
 */
public interface AddFriendView {

    /**
     * 显示查询的结果
     * @param friendList
     * @param unFriendList
     */
    public void showSearchResultList(List<User> friendList, List<User> unFriendList);

    /**
     * 显示请求添加好友的结果
     */
    public void addFriend();

    /**
     * 显示错误
     */
    public void showError();

    /**
     * 获得界面的Activity
     * @return
     */
    public Activity getActivity();
}
