package com.starun.www.starun.presenter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.baidu.mapapi.model.LatLng;
import com.starun.www.starun.presenter.MapPresenter;
import com.starun.www.starun.pview.BaiduMapView;

import java.util.List;

/**
 * Created by yearsj on 2016/4/14.
 */
public class MapPresenterImpl implements MapPresenter{
    private BaiduMapView baiduMapView = null;
    private MsgReceiver msgReceiver = null;

    public MapPresenterImpl(){}

    public MapPresenterImpl(BaiduMapView baiduMapView){
        this.baiduMapView = baiduMapView;
    }

    @Override
    public void registerReceiver() {
        if(null == msgReceiver){
            msgReceiver = new MsgReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.starun.www.starun.POINTLIST");
            baiduMapView.getActivity().registerReceiver(msgReceiver, intentFilter);
        }
    }

    @Override
    public void unregisterReceiver() {
        if(null!=msgReceiver){
            baiduMapView.getActivity().unregisterReceiver(msgReceiver);
            msgReceiver = null;
        }
    }

    /**
     * 广播接收器
     * @author len
     *
     */
    public class MsgReceiver extends BroadcastReceiver {

        public MsgReceiver(){
        }
        @Override
        public void onReceive(Context context, Intent intent) {
            //拿到进度，更新UI
            Bundle bundle = intent.getExtras();
            List<LatLng> pointList = bundle.getParcelableArrayList("pointList");
            double distance = intent.getDoubleExtra("distance", 0) / 1000;
            if(null!=pointList&&0!=pointList.size()) {
                baiduMapView.drawRealtimePoint(pointList);
            }
            baiduMapView.showInfo(distance);
        }
    }
}
