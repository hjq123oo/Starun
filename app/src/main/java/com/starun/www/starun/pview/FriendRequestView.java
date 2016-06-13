package com.starun.www.starun.pview;

import android.app.Activity;

import com.starun.www.starun.server.data.User;

import java.util.List;


/**
 * Created by hjq on 2016/6/6.
 */
public interface FriendRequestView {
    Activity getActivity();


    void onFriendRequestShow(List<User> users);

    void onFriendRequestUpdate();

}
