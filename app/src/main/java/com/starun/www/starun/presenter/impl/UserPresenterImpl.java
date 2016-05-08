package com.starun.www.starun.presenter.impl;

import com.starun.www.starun.server.data.User;
import com.starun.www.starun.presenter.UserPresenter;
import com.starun.www.starun.pview.UserView;
import com.starun.www.starun.server.service.UserService;

/**
 * Created by yearsj on 2016/4/28.
 */
public class UserPresenterImpl implements UserPresenter{
    private UserView userView = null;
    private UserService userService = null;

    public UserPresenterImpl(){}

    public UserPresenterImpl(UserView userView){
        this.userView = userView;
        this.userService = new UserService();
    }
    @Override
    public void login(User user) {
        String response = userService.login(user);
        if("true".equals(response)){
            userView.onSuccess();
        }
        else{
            userView.onFailure(response);
        }
    }

    @Override
    public void register(User user) {
        String response = userService.register(user);
        if("true".equals(response)){
            userView.onSuccess();
        }
        else{
            userView.onFailure(response);
        }
    }
}
