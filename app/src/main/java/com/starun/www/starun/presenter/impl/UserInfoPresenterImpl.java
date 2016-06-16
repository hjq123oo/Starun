package com.starun.www.starun.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.presenter.UserInfoPresenter;
import com.starun.www.starun.pview.UserInfoView;
import com.starun.www.starun.server.data.RunTotalInfo;
import com.starun.www.starun.server.util.ConnectUtil;
import com.starun.www.starun.view.application.MyApplication;


import java.util.Map;

/**
 * Created by hjq on 2016/6/4.
 */
public class UserInfoPresenterImpl implements UserInfoPresenter{
    private UserInfoView userInfoView;
    private int ownUserId;

    private int user_id;
    RunTotalInfo runTotalInfo;


    private static final int FAILURE = 0;
    private static final int OWN_SHOW = 1;
    private static final int FRIEND_SHOW = 2;
    private static final int NON_FRIEND_SHOW = 3;
    private static final int FRIEND_ADD = 4;
    private static final int FRIEND_DELETE = 5;
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //判断发送的消息
                case FAILURE:
                    break;
                case OWN_SHOW:
                    userInfoView.onShowOwn(runTotalInfo);
                    break;
                case FRIEND_SHOW:
                    userInfoView.onShowFriend(runTotalInfo);
                    break;
                case NON_FRIEND_SHOW:
                    userInfoView.onShowNonFriend(runTotalInfo);
                    break;
                case FRIEND_ADD:
                    userInfoView.onShowFriendAdd();
                    break;
                case FRIEND_DELETE:
                    userInfoView.onShowFriendDelete();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public UserInfoPresenterImpl(UserInfoView userInfoView){
        this.userInfoView = userInfoView;
        ownUserId = ((MyApplication)userInfoView.getActivity().getApplication()).getUser().getUser_id();
    }

    @Override
    public void doLoadUserInfo(int userId) {
        user_id = userId;
        if(userId == ownUserId){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    String message = "user_id="+user_id;
                    String response = ConnectUtil.getResponse("detailOfUser", message);
                    String result = null;
                    JSONObject jsonObject = JSON.parseObject(response);

                    if(null!=jsonObject){
                        result= jsonObject.getString("result");
                    }
                    if("true".equals(result)&&null!=result){
                        JSONObject msgObj = jsonObject.getJSONObject("msg");
                        runTotalInfo = JSON.parseObject(msgObj.getString("runTotalInfo"), new TypeReference<RunTotalInfo>() {
                        });

                        myHandler.sendEmptyMessage(OWN_SHOW);
                    }
                    else if(result == null){

                    }else if(result.equals("false")){
                        runTotalInfo = new RunTotalInfo();
                        runTotalInfo.setUser_id(user_id);
                        runTotalInfo.setTotal_time(0);
                        runTotalInfo.setPlan_percentage(0);
                        runTotalInfo.setTotal_distance(0);

                        myHandler.sendEmptyMessage(OWN_SHOW);
                    }
                }
            }.start();

        }else{
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    String message = "user_id="+user_id+"&me_id="+ownUserId;
                    String response = ConnectUtil.getResponse("detailOfFriend", message);
                    String result = null;
                    Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                    });

                    if(null!=map){
                        result= map.get("result");
                    }
                    if("true".equals(result)&&null!=result){
                        String msg = map.get("msg");
                        Map<String, String> msgMap = JSON.parseObject(msg, new TypeReference<Map<String, String>>() {
                        });

                        boolean isfriend = JSON.parseObject(msgMap.get("isfriend"),new TypeReference<Boolean>(){});
                        runTotalInfo = JSON.parseObject(msgMap.get("runTotalInfo"),new TypeReference<RunTotalInfo>(){});
                        if(isfriend){
                            myHandler.sendEmptyMessage(FRIEND_SHOW);
                        }else {
                            myHandler.sendEmptyMessage(NON_FRIEND_SHOW);
                        }


                    }
                    else{
                        myHandler.sendEmptyMessage(FAILURE);
                    }
                }
            }.start();
        }
    }

    @Override
    public void doFriendAdd() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String message = "user_id1="+ownUserId+"&user_id2="+user_id;
                String response =  ConnectUtil.getResponse("addFriendInfo", message);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }
                if("true".equals(result)&&null!=result){
                    myHandler.sendEmptyMessage(FRIEND_ADD);
                }
                else if(result == null){
                    myHandler.sendEmptyMessage(FAILURE);
                }else if(result.equals("false")){
                    myHandler.sendEmptyMessage(FRIEND_ADD);
                }
            }
        }.start();
    }

    @Override
    public void doFriendDelete() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String message = "user_id1="+ownUserId+"&user_id2="+user_id;
                String response =  ConnectUtil.getResponse("delete", message);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }
                if("true".equals(result)&&null!=result){
                    myHandler.sendEmptyMessage(FRIEND_DELETE);
                }
                else{
                    myHandler.sendEmptyMessage(FAILURE);
                }
            }
        }.start();
    }
}
