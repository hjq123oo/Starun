package com.starun.www.starun.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.presenter.AvatarPresenter;
import com.starun.www.starun.pview.AvatarView;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.util.ConnectUtil;
import com.starun.www.starun.view.application.MyApplication;

import java.util.Map;

/**
 * Created by hjq on 2016/6/5.
 */
public class AvatarPresenterImpl implements AvatarPresenter{
    private AvatarView avatarView;

    private static final int FAILURE = 0;
    private static final int SUCCESS = 1;


    public AvatarPresenterImpl(AvatarView avatarView){
        this.avatarView = avatarView;

    }


    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //判断发送的消息
                case SUCCESS:
                    avatarView.onAvatarUpload();
                    break;

                case FAILURE:
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void doAvatarUpload(final String avatarFileName) {
        new Thread(){
            @Override
            public void run() {
                super.run();

                User user = ((MyApplication) avatarView.getActivity().getApplication()).getUser();

                user.setHeadImgPath(avatarFileName);
                String message = JSON.toJSONString(user);

                String response =  ConnectUtil.getResponsePostJson("updateUserInfo ", message);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }
                if("true".equals(result)&&null!=result){
                    ((MyApplication) avatarView.getActivity().getApplication()).setUser(user);
                    myHandler.sendEmptyMessage(SUCCESS);
                }
                else{
                    myHandler.sendEmptyMessage(FAILURE);
                }
            }
        }.start();
    }
}
