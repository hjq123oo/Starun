package com.starun.www.starun.pview;

import android.app.Activity;

import com.starun.www.starun.server.data.RunTotalInfo;
import com.starun.www.starun.server.data.User;

import java.util.List;

/**
 * Created by yearsj on 2016/5/17.
 */
public interface FriendOrRankListView {
    /**
     * 显示按照日常跑步的好友列表
     * @param users
     */
    public void showUserList(List<User> users);

    /**
     * 显示详细信息
     * @param runTotalInfo
     * @param isfriend
     */
    public void showFriendDetail(RunTotalInfo runTotalInfo, boolean isfriend);

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
