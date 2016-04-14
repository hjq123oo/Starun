package com.starun.www.starun.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.starun.www.starun.R;
import com.starun.www.starun.presenter.MapPresenter;
import com.starun.www.starun.presenter.impl.MapPresenterImpl;
import com.starun.www.starun.pview.BaiduMapView;
import java.util.List;

/**
 * Created by yearsj on 2016/4/10.
 **/
public class MapActivity extends Activity implements BaiduMapView {
    MapView mMapView = null;
    BaiduMap baiduMap = null;
    /**
     * 图标
     */
    private static BitmapDescriptor realtimeBitmap;

    // 覆盖物
    protected static OverlayOptions overlay;
    // 路线覆盖物
    private static PolylineOptions polyline = null;

    private static MapStatusUpdate msUpdate = null;

    private MapPresenter mapPresenter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        baiduMap = mMapView.getMap();
        mapPresenter = new MapPresenterImpl(this);
        //mMapView.showZoomControls(false);

        //注册广播
        mapPresenter.registerReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        Toast.makeText(this,"mapActivity-onDestroy",Toast.LENGTH_LONG).show();
        mMapView.onDestroy();
        mapPresenter.unregisterReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        Toast.makeText(this,"mapActivity-onResume",Toast.LENGTH_LONG).show();
        mMapView.onResume();

        //动态注册广播接收器
        mapPresenter.registerReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        Toast.makeText(this,"mapActivity-onPause",Toast.LENGTH_LONG).show();
        mMapView.onPause();
        //动态注册广播接收器
        mapPresenter.unregisterReceiver();
    }

    /**
     * 绘制实时点
     */
    public void drawRealtimePoint(List<LatLng> pointList) {

        LatLng point = pointList.get(pointList.size() - 1);
        baiduMap.clear();
        MapStatus mMapStatus = new MapStatus.Builder().target(point).zoom(18).build();

        msUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);

        realtimeBitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.mapmarker);

        overlay = new MarkerOptions().position(point)
                .icon(realtimeBitmap).zIndex(9).draggable(true);

        if (pointList.size() >= 2 && pointList.size() <= 10000) {
            // 添加路线（轨迹）
            polyline = new PolylineOptions().width(10)
                    .color(Color.RED).points(pointList);
        }

        addMarker();

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * 添加地图覆盖物
     */
    protected  void addMarker() {

        if (null != msUpdate) {
            baiduMap.setMapStatus(msUpdate);
        }

        // 路线覆盖物
        if (null != polyline) {
            baiduMap.addOverlay(polyline);
        }

        // 实时点覆盖物
        if (null != overlay) {
            baiduMap.addOverlay(overlay);
        }
    }
}
