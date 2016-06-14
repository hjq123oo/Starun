package com.starun.www.starun.presenter.impl;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.presenter.AddFriendPresenter;
import com.starun.www.starun.pview.AddFriendView;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.util.ConnectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yearsj on 2016/5/17.
 */
public class AddFriendPresenterImpl implements AddFriendPresenter{
    private AddFriendView addFriendView = null;
    private static final int SEARCH = 1;
    private static final int ADD = 2;
    private static final int FAILURE = 0;

    public AddFriendPresenterImpl(AddFriendView addFriendView){
        this.addFriendView = addFriendView;
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //判断发送的消息
                case SEARCH:
                    String strlist = (String) msg.obj;
                    Map<String,String> strmap = JSON.parseObject(strlist,new TypeReference<Map<String,String>>(){});
                    List<User> friendList = new ArrayList<User>();
                    List<User> unfriendList = new ArrayList<User>();
                    JSONArray arr1 = JSON.parseArray(strmap.get("friendList"));
                    JSONArray arr2 = JSON.parseArray(strmap.get("unfriendList"));
                    for(int i=0;i<arr1.size();i++){
                        JSONArray dataArr = JSON.parseArray(arr1.getString(i));
                        User user = new User();
                        user.setUser_id((int) dataArr.get(0));
                        user.setUsername((String) dataArr.get(1));
                        user.setNickname((String)dataArr.get(3));
                        user.setHeadImgPath((String)dataArr.get(8));
                        friendList.add(user);
                    }
                    for(int i=0;i<arr2.size();i++){
                        JSONArray dataArr = JSON.parseArray(arr2.getString(i));
                        User user = new User();
                        user.setUser_id((int) dataArr.get(0));
                        user.setUsername((String) dataArr.get(1));
                        user.setNickname((String)dataArr.get(3));
                        user.setHeadImgPath((String)dataArr.get(8));
                        unfriendList.add(user);
                    }
                    addFriendView.showSearchResultList(friendList,unfriendList);
                    break;
                case ADD:
                    addFriendView.addFriend();
                    break;
                case FAILURE:
                    addFriendView.showError();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void getSearchResultList(final String keyword,final int user_id) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String message = "user_id="+user_id+"&&user_name="+keyword;
                String response =  ConnectUtil.getResponse("search", message);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }
                if("true".equals(result)&&null!=result){
                    String result_str = map.get("msg");
                    Message msg = new Message();
                    msg.what = SEARCH;
                    msg.obj = result_str;
                    myHandler.sendMessage(msg);
                }
                else{
                    myHandler.sendEmptyMessage(FAILURE);
                }
            }
        }.start();
    }

    @Override
    public void addFriend(final String user_id1,final String user_id2) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String message = "user_id1="+user_id1+"&user_id2="+user_id2;
                String response =  ConnectUtil.getResponse("addFriendInfo", message);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }
                if("true".equals(result)&&null!=result){
                    Message msg = new Message();
                    msg.what = ADD;
                    myHandler.sendMessage(msg);
                }
                else{
                    myHandler.sendEmptyMessage(FAILURE);
                }
            }
        }.start();
    }
}
