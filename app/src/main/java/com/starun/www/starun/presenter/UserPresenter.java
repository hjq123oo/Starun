package com.starun.www.starun.presenter;

import com.starun.www.starun.server.data.User;

/**
 * Created by yearsj on 2016/4/28.
*/
public interface UserPresenter {
    /**
     * 用户登录
     */
    public void login(User user);

    /**
     * 用户注册
     */
    public void register(User user);
}
