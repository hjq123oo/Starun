package com.starun.www.starun.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.starun.www.starun.R;

public class FriendNoticeService extends Service {
    public static final String ACTION = "com.starun.www.starun.service.FRIEND_NOTICE_SERVICE";

    private Notification mNotification;
    private NotificationManager mManager;

    private static final int NOTIFICATION_ID = 1;


    public FriendNoticeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        initNotifiManager();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);
    }

    //初始化通知栏配置
    private void initNotifiManager() {
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    //弹出Notification
    private void showNotification() {
/*        mNotification.when = System.currentTimeMillis();

        int icon = R.drawable.ic_launcher;
        mNotification = new Notification();
        mNotification.icon = icon;
        mNotification.tickerText = "您有新的好友请求";
        mNotification.defaults |= Notification.DEFAULT_SOUND;
        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent i = new Intent(this, FriendRequestActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, Intent.FLAG_ACTIVITY_NEW_TASK);
        mNotification.setLatestEventInfo(this,getResources().getString(R.string.app_name), "You have new message!", pendingIntent);
        mManager.notify(0, mNotification);*/
    }

}
