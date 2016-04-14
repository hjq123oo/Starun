package com.starun.www.starun.pview;

import android.app.Activity;

import com.baidu.mapapi.model.LatLng;

import java.util.List;

/**
 * Created by yearsj on 2016/4/14.
 */
public interface BaiduMapView {
    /**
     * 更新地图
     * @param pointList
     */
    public void drawRealtimePoint(List<LatLng> pointList);

    public Activity getActivity();
}
