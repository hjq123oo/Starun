package com.starun.www.starun.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.R;
import com.starun.www.starun.presenter.FriendRequestPresenter;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.util.ConnectUtil;
import com.starun.www.starun.view.application.MyApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FriendNoticeService extends Service {
    public static final String ACTION = "com.starun.www.starun.service.FRIEND_NOTICE_SERVICE";

    private Notification mNotification;
    private NotificationManager mManager;

    private static final int NOTIFICATION_ID = 1;
    private Notification notify1;

    private List<User> users;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(  ((MyApplication)getApplication()).getUser() != null){
            if(((MyApplication)getApplication()).getSetting().isHasNotice()){
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        String message = "user_id="+((MyApplication)getApplication()).getUser().getUser_id();;
                        String response = ConnectUtil.getResponse("getNotificationForState", message);
                        String result = null;
                        Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                        });

                        if(null!=map){
                            result= map.get("result");
                        }

                        if("true".equals(result) &&null!=result){

                            users = new ArrayList<User>();

                            String msg = map.get("msg");
                            JSONObject obj = JSON.parseObject(msg);
                            JSONArray arr = JSON.parseArray(obj.getJSONObject("user").getString("resultarr"));
                            for(int i=0;i<arr.size();i++){
                                JSONArray dataArr = JSON.parseArray(arr.getString(i));
                                User myUser = new User();
                                myUser.setUser_id((int)dataArr.get(0));
                                myUser.setUsername((String) dataArr.get(1));
                                myUser.setNickname((String)dataArr.get(3));
                                myUser.setHeadImgPath((String)dataArr.get(8));
                                users.add(myUser);
                            }

                            if(users.size()>0){
                                initNotifiManager();
                                showNotification();
                            }

                        }
                        else{
                        }
                    }
                }.start();
            }

        }

        return super.onStartCommand(intent, flags, startId);
    }

    //初始化通知栏配置
    private void initNotifiManager() {


        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent it = new Intent(this, FriendRequestPresenter.class);
        PendingIntent pit = PendingIntent.getActivity(this, 0, it, 0);

        Notification.Builder mBuilder = new Notification.Builder(this);
        mBuilder.setContentTitle("Starun呼呼跑步")
                .setContentText("您有新的好友添加请求")
                .setTicker("您有新的好友添加请求")
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true)
                .setContentIntent(pit);

        notify1 = mBuilder.build();

    }

    //弹出Notification
    private void showNotification() {
        mManager.notify(NOTIFICATION_ID, notify1);
    }

}
