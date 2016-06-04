package com.starun.www.starun.presenter;

/**
 * Created by hjq on 2016/6/4.
 */
public interface UserInfoPresenter {

    /**
     * 加载用户信息
     * @param userId
     */
    void doLoadUserInfo(int userId);

    /**
     * 添加好友
     */
    void doFriendAdd();

    /**
     * 删除好友
     */
    void doFriendDelete();
}
