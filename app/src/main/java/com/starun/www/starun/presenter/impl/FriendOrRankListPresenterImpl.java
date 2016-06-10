package com.starun.www.starun.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.presenter.FriendOrRankListPresenter;
import com.starun.www.starun.pview.FriendOrRankListView;
import com.starun.www.starun.server.data.RunTotalInfo;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.util.ConnectUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by yearsj on 2016/5/17.
 */
public class FriendOrRankListPresenterImpl implements FriendOrRankListPresenter {

    private FriendOrRankListView friendListView = null;
    private boolean rank = false;
    private static final int FORUSER = 1;
    private static final int DETAIL = 3;
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
                    String strlist1 = (String) msg.obj;
                    List<User> users1 = JSON.parseObject(strlist1, new TypeReference<List<User>>(){});
                    friendListView.showUserList(users1);
                    break;
                case DETAIL:
                    String str = (String) msg.obj;
                    if(rank){
                        Map<String,String> strmap = JSON.parseObject(str,new TypeReference<Map<String,String>>(){});
                        String isfriend = strmap.get("isfriend");
                        String runstr = strmap.get("RunTotalInfo");
                        RunTotalInfo runTotalInfo = JSON.parseObject(runstr, new TypeReference<RunTotalInfo>() {
                        });
                        friendListView.showFriendDetail(runTotalInfo,Boolean.valueOf(isfriend));
                    }
                    else{
                        RunTotalInfo runTotalInfo = JSON.parseObject(str,new TypeReference<RunTotalInfo>(){});
                        friendListView.showFriendDetail(runTotalInfo,true);
                    }
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
    public void showFriendDetail(final String user_id) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String message = "user_id="+user_id;
                String response = null;
                if(rank){
                    response = ConnectUtil.getResponse("detailOfUser", message);
                }
                else{
                    response = ConnectUtil.getResponse("detailOfFriend", message);
                }
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }
                if("true".equals(result)&&null!=result){
                    String detail = map.get("msg");
                    Message msg = new Message();
                    msg.what = DETAIL;
                    msg.obj = detail;
                    myHandler.sendMessage(msg);
                }
                else{
                    myHandler.sendEmptyMessage(FAILURE);
                }
            }
        }.start();
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
            String friendlist = jsonObject.getJSONObject("msg").getString("users");
            Message msg = new Message();
            msg.what = FORUSER;
            msg.obj = friendlist;
            myHandler.sendMessage(msg);
        }
        else{
            myHandler.sendEmptyMessage(FAILURE);
        }
    }
}
