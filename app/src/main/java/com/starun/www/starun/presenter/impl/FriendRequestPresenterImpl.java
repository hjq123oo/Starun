package com.starun.www.starun.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.presenter.FriendRequestPresenter;
import com.starun.www.starun.pview.FriendRequestView;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.util.ConnectUtil;
import com.starun.www.starun.view.application.MyApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hjq on 2016/6/6.
 */
public class FriendRequestPresenterImpl implements FriendRequestPresenter {
    private FriendRequestView friendRequestView;
    private int user_id;


    //map数据：map<"userNotification",UserNotification>;map<"user",User>
    List<Map<String,Object>> mapList;

    List<User> users;

    private static final int FAILURE = 0;
    private static final int LOAD= 1;
    private static final int AGREE = 2;
    private static final int DISAGREE = 3;

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //判断发送的消息
                case FAILURE:
                    break;
                case LOAD:
                    friendRequestView.onFriendRequestShow(users);
                    break;
                case AGREE:
                    friendRequestView.onFriendRequestUpdate();
                    break;
                case DISAGREE:
                    friendRequestView.onFriendRequestUpdate();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public FriendRequestPresenterImpl(FriendRequestView friendRequestView){
        this.friendRequestView = friendRequestView;
        user_id = ((MyApplication)friendRequestView.getActivity().getApplication()).getUser().getUser_id();
        mapList = new ArrayList<Map<String,Object>>();
        users = new ArrayList<User>();
    }


    @Override
    public void doFriendRequestLoad() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String message = "user_id="+user_id;
                String response = ConnectUtil.getResponse("getNotificationForOperated", message);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }

                if("true".equals(result) &&null!=result){
                    String msg = map.get("msg");
                    mapList = JSON.parseObject(msg,new TypeReference< List<Map<String,Object>>>(){});

                    for(Map<String,Object> myMap : mapList){
                        users.add((User) myMap.get("user"));
                    }

                    myHandler.sendEmptyMessage(LOAD);
                }
                else{
                    myHandler.sendEmptyMessage(FAILURE);
                }
            }
        }.start();
    }

    @Override
    public void doFriendAgree(final int targetUserPosition) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String message = "user_id1="+user_id+"&user_id2="+users.get(targetUserPosition).getUser_id();
                String response = ConnectUtil.getResponse("addFriend", message);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }

                if("true".equals(result) &&null!=result){
                    users.remove(targetUserPosition);
                    myHandler.sendEmptyMessage(AGREE);
                }
                else{
                    myHandler.sendEmptyMessage(FAILURE);
                }
            }
        }.start();
    }

    @Override
    public void doFriendDisagree(final int targetUserPosition) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String message = "user_id1="+user_id+"&user_id2="+users.get(targetUserPosition).getUser_id();
                String response = ConnectUtil.getResponse("disagreeFriend", message);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }

                if("true".equals(result) &&null!=result){
                    users.remove(targetUserPosition);
                    myHandler.sendEmptyMessage(DISAGREE);
                }
                else{
                    myHandler.sendEmptyMessage(FAILURE);
                }
            }
        }.start();
    }
}
