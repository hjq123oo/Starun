package com.starun.www.starun.pview;

import android.app.Activity;

import com.starun.www.starun.server.data.RunTotalInfo;

/**
 * Created by hjq on 2016/6/4.
 */
public interface UserInfoView {
    void onShowOwn(RunTotalInfo runTotalInfo);

    void onShowFriend(RunTotalInfo runTotalInfo);

    void onShowNonFriend(RunTotalInfo runTotalInfo);

    Activity getActivity();
}
