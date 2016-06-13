package com.starun.www.starun.presenter;

/**
 * Created by hjq on 2016/6/6.
 */
public interface FriendRequestPresenter {
    void doFriendRequestLoad();

    void doFriendAgree(int targetUserId);

    void doFriendDisagree(int targetUserId);
}
