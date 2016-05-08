package com.starun.www.starun.server.service;

import com.alibaba.fastjson.JSON;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.util.ConnectThread;

/**
 * Created by yearsj on 2016/4/28.
 */
public class UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    public String register(User user){
        String userMessage = JSON.toJSONString(user);
        ConnectThread  connectThread = new ConnectThread("register",userMessage);
        return connectThread.getResponse();
    }

    /**
     * 登陆用户
     * @param user
     * @return
     */
    public String login(User user){
        String userMessage = JSON.toJSONString(user);
        ConnectThread  connectThread = new ConnectThread("login",userMessage);
        return connectThread.getResponse();
    }
}
