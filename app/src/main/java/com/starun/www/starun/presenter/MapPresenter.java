package com.starun.www.starun.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.baidu.mapapi.model.LatLng;

import java.util.List;

/**
 * Created by yearsj on 2016/4/14.
 */
public interface MapPresenter {
    /**
     * 注册广播
     */
    void registerReceiver();

    /**
     * 注销广播
     */
    void unregisterReceiver();
}
