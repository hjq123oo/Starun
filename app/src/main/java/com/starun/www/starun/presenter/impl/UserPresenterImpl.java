package com.starun.www.starun.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.presenter.UserPresenter;
import com.starun.www.starun.pview.UserView;
import com.starun.www.starun.server.util.ConnectUtil;

import java.util.Map;

/**
 * Created by yearsj on 2016/4/28.
 */
public class UserPresenterImpl implements UserPresenter{
    private UserView userView = null;
    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;

    public UserPresenterImpl(){}

    public UserPresenterImpl(UserView userView){
        this.userView = userView;
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //判断发送的消息
                case SUCCESS:userView.onSuccess(msg.obj.toString());break;
                case FAILURE:userView.onFailure();break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void login(final User user) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String userMessage = "name="+user.getUsername()+"&pw="+user.getPassword();
                String response = ConnectUtil.getResponse("login", userMessage);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }
                if("true".equals(result)&&null!=result){
                    Message msg = new Message();
                    msg.what = SUCCESS;
                    msg.obj = "注册成功!";
                    myHandler.sendMessage(msg);
                }
                else{
                    myHandler.sendEmptyMessage(FAILURE);
                }
            }
        }.start();
    }

    @Override
    public void register(final User user) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String userMessage = "name="+user.getUsername()+"&pw="+user.getPassword()+"&nickname="+user.getNickname();
                String response = ConnectUtil.getResponse("register", userMessage);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }
                if("true".equals(result)&&null!=result){
                    myHandler.sendEmptyMessage(SUCCESS);
                }
                else{
                    myHandler.sendEmptyMessage(FAILURE);
                }
            }
        }.start();
    }
}
