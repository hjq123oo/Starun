package com.starun.www.starun.presenter.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.presenter.FriendOrRankListPresenter;
import com.starun.www.starun.pview.FriendOrRankListView;
import com.starun.www.starun.server.data.RunTotalInfo;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.util.ConnectUtil;
import com.starun.www.starun.view.UserInfoActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yearsj on 2016/5/17.
 */
public class FriendOrRankListPresenterImpl implements FriendOrRankListPresenter {

    private FriendOrRankListView friendListView = null;
    private boolean rank = false;
    private static final int FORUSER = 1;
    private static final int FAILURE = 0;

    public FriendOrRankListPresenterImpl(){}

    public FriendOrRankListPresenterImpl(FriendOrRankListView friendListView, boolean rank){
        this.friendListView = friendListView;
        this.rank = rank;
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //判断发送的消息
                case FORUSER:
                    JSONObject strlist1 = (JSONObject) msg.obj;
                    JSONArray arr = JSON.parseArray(strlist1.getString("users"));
                    List<User> users1 = new ArrayList<User>();
                    for(int i=0;i<arr.size();i++){
                        JSONArray dataArr = JSON.parseArray(arr.getString(i));
                        User user = new User();
                        user.setUser_id((int) dataArr.get(0));
                        user.setUsername((String) dataArr.get(1));
                        user.setNickname((String)dataArr.get(3));
                        user.setHeadImgPath((String)dataArr.get(8));
                        users1.add(user);
                    }
                    friendListView.showUserList(users1);
                    break;
                case FAILURE:
                    friendListView.showError();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    public void showListForPlan(final String user_id) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String message = "user_id="+user_id;
                getMessage("friendlist/forplan",message);
            }
        }.start();
    }

    @Override
    public void showListForDaily(final String user_id) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String message = "user_id="+user_id;
                getMessage("friendlist/fordaily",message);
            }
        }.start();
    }


    @Override
    public void showListForPlanRank() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                getMessage("rank/forplan",null);
            }
        }.start();
    }

    @Override
    public void showListForDailyRank() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                getMessage("rank/fordaily",null);
            }
        }.start();
    }

    @Override
    public void showFriendDetail(final User user) {
        Intent intent = new Intent();
        intent.setClass(friendListView.getActivity(), UserInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        friendListView.getActivity().startActivity(intent);
    }

    private void getMessage(String operationUrl, String message){
        String response = null;
        response = ConnectUtil.getResponse(operationUrl, message);
        String result = null;
        JSONObject jsonObject = JSON.parseObject(response);

        if(null!=jsonObject){
            result= jsonObject.getString("result");
        }
        if("true".equals(result)&&null!=result){
            JSONObject msgObj= jsonObject.getJSONObject("msg");
            Message msg = new Message();
            msg.what = FORUSER;
            msg.obj = msgObj;
            myHandler.sendMessage(msg);
        }
        else{
            myHandler.sendEmptyMessage(FAILURE);
        }
    }
}
