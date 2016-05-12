package com.starun.www.starun.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.OnEntityListener;
import com.baidu.trace.OnStartTraceListener;
import com.baidu.trace.OnStopTraceListener;
import com.baidu.trace.Trace;
import com.baidu.trace.TraceLocation;
import com.starun.www.starun.presenter.impl.RunPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yearsj on 2016/4/10.
 **/
/**
 * 1.自定义线程 -- 执行轨迹查询
 * 2.开启轨迹监听，在轨迹监听器重开启轨迹实体查询线程
 * 3.定义实体监听器，在回调函数中将当前轨迹点加入轨迹集合中
 * 4.根据轨迹点计算总路程，将其显示在跑步主界面中
 * 5.将轨迹集合发送给对应的显示地图界面，显示地图
 */
public class TraceService extends Service {
    private int traceType = 2;  //轨迹服务类型
    private int gatherInterval = 3;  //位置采集周期 (s)
    private int packInterval = 10;  //打包周期 (s)
    private String entityName = null;  // entity标识
    private long serviceId = 114750;// 鹰眼服务ID
    private static OnStartTraceListener startTraceListener = null;  //开启轨迹服务监听器
    protected static OnStopTraceListener stopTraceListener = null; //停止轨迹服务监听器
    private static OnEntityListener entityListener = null;
    private RefreshThread refreshThread = null;  //刷新地图线程以获取实时点
    private static ArrayList<LatLng> pointList = new ArrayList<LatLng>();  //定位点的集合 --- 需要传递
    private Trace trace;  // 实例化轨迹服务
    private LBSTraceClient client;  // 实例化轨迹服务客户端
    private double distance = 0;     //总公里数 -- 需要传递

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        init();  //初始化各个参数
        initOnEntityListener();  //初始化实体监听器
        initOnStartTraceListener();  //初始化轨迹追踪监听器
        initOnStopTraceListener();  //初始化停止轨迹服务监听器
        addEntity();                //添加实体
        client.startTrace(trace, startTraceListener);  // 开启轨迹服务
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        client.stopTrace(trace, stopTraceListener);
        client.onDestroy();
        //android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 初始化各个参数
     */
    private void init() {
        entityName = "temp";  //手机Imei值的获取，用来充当实体名
        client = new LBSTraceClient(getApplicationContext());  //实例化轨迹服务客户端
        trace = new Trace(getApplicationContext(), serviceId, entityName, traceType);  //实例化轨迹服务
        client.setInterval(gatherInterval, packInterval);  //设置位置采集和打包周期
        client.setProtocolType(0); //设置请求协议
    }

    /**
     * 添加entity
     */
    private void addEntity() {
        // 属性名称（格式 : "key1=value1,columnKey2=columnValue2......."）
        String columnKey = "";
        client.addEntity(serviceId, entityName, columnKey, entityListener);
    }

    /**
     * 初始化设置实体状态监听器
     */
    private void initOnEntityListener(){
        //实体状态监听器
        entityListener = new OnEntityListener(){
            @Override
            public void onRequestFailedCallback(String arg0) {
                Looper.prepare();
                Toast.makeText(
                        getApplicationContext(),
                        "entity请求失败的回调接口信息：" + arg0,
                        Toast.LENGTH_SHORT)
                        .show();
                Looper.loop();
            }

            @Override
            public void onAddEntityCallback(String arg0) {
                Looper.prepare();
                Toast.makeText(getApplicationContext(),
                        "添加entity回调接口消息 : " + arg0, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onQueryEntityListCallback(String s) {
                super.onQueryEntityListCallback(s);
            }

            @Override
            public void onReceiveLocation(TraceLocation traceLocation) {
                super.onReceiveLocation(traceLocation);
                /**
                 * 查询实体集合回调函数，此时调用实时轨迹方法
                 */
                addToRealtimeTrack(traceLocation);
            }
        };
    }

    /**
     *  追踪开始
     */
    private void initOnStartTraceListener() {

        // 实例化开启轨迹服务回调接口
        startTraceListener = new OnStartTraceListener() {
            // 开启轨迹服务回调接口（arg0 : 消息编码，arg1 : 消息内容，详情查看类参考）
            @Override
            public void onTraceCallback(int arg0, String arg1) {
                Log.i("TAG", "onTraceCallback=" + arg1);
                if(arg0 == 0 || arg0 == 10006){
                    startRefreshThread(true);
                }
            }

            // 轨迹服务推送接口（用于接收服务端推送消息，arg0 : 消息类型，arg1 : 消息内容，详情查看类参考）
            @Override
            public void onTracePushCallback(byte arg0, String arg1) {
                Log.i("TAG", "onTracePushCallback=" + arg1);
            }
        };

    }

    /**
     * 初始化OnStopTraceListener
     */
    private void initOnStopTraceListener() {
        // 初始化stopTraceListener
        stopTraceListener = new OnStopTraceListener() {

            // 轨迹服务停止成功
            public void onStopTraceSuccess() {
                // TODO Auto-generated method stub
                startRefreshThread(false);
            }

            // 轨迹服务停止失败（arg0 : 错误编码，arg1 : 消息内容，详情查看类参考）
            public void onStopTraceFailed(int arg0, String arg1) {
                // TODO Auto-generated method stub
                Log.i("TAG","停止轨迹服务接口消息 [错误编码 : " + arg0 + "，消息内容 : " + arg1 + "]");
            }
        };
    }

    /**
     * 启动刷新线程
     */
    private void startRefreshThread(boolean isStart){
        if(refreshThread == null){
            refreshThread = new RefreshThread();
        }

        refreshThread.refresh = isStart;

        if(isStart){
            if(!refreshThread.isAlive()){
                refreshThread.start();
            }
        }
        else{
            refreshThread = null;
        }

    }

    /**
     * 轨迹刷新线程
     */
    private class RefreshThread extends Thread{

        protected boolean refresh = true;

        public void run(){

            while(refresh){
                queryRealtimeTrack();
                try{
                    Thread.sleep(packInterval * 1000);
                }catch(InterruptedException e){
                    System.out.println("线程休眠失败");
                }
            }

        }
    }


    /**
     * 查询实体列表
     */
    private void queryEntityList() {
        // 属性名称（格式为 : "key1=value1,key2=value2,....."）
        String columnKey = "";
        // 返回结果的类型（0 : 返回全部结果，1 : 只返回entityName的列表）
        int returnType = 0;
        // 活跃时间（指定该字段时，返回从该时间点之后仍有位置变动的entity的实时点集合）-- 离当前系统30秒前
        int activeTime = (int) (System.currentTimeMillis() / 1000 - 30);
        // 分页大小
        int pageSize = 10;
        // 分页索引
        int pageIndex = 1;
        //执行查询
        client.queryEntityList(serviceId, entityName, columnKey, returnType, activeTime,pageSize,pageIndex, entityListener);
    }

    /**
     * 查询当前实时位置
     */
    protected  void queryRealtimeTrack(){
        client.queryRealtimeLoc(serviceId,entityListener);
    }

    /**
     * 显示实时轨迹
     */
    protected void addToRealtimeTrack(TraceLocation location) {

        if (null == refreshThread || !refreshThread.refresh) {
            return;
        }

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        if (Math.abs(latitude - 0.0) < 0.000001 && Math.abs(longitude - 0.0) < 0.000001) {
            Toast.makeText(getApplicationContext(),"当前查询无轨迹点",Toast.LENGTH_SHORT).show();
        } else {
            LatLng latLng = new LatLng(latitude, longitude);
            //将当前的位置点添入集合中
            pointList.add(latLng);
            calRealtimeMiles();

            //发送广播
            Intent distanceIntent = new Intent("com.starun.www.starun.DISTANCE");
            distanceIntent.putExtra("distance", distance);
            distanceIntent.putExtra("entityName", entityName);
            sendBroadcast(distanceIntent);

            Intent pointlistIntent = new Intent("com.starun.www.starun.POINTLIST");
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("pointList", pointList);
            distanceIntent.putExtra("distance", distance);
            pointlistIntent.putExtras(bundle);
            sendBroadcast(pointlistIntent);
        }

    }

    /**
     * 计算实时总公里数
     */
    private void calRealtimeMiles(){
        if(pointList == null){
            return;
        }
        //获得总的定位点
        int size = pointList.size();

        if(size<=1) {
            return;
        }
        distance+=DistanceUtil.getDistance(pointList.get(size-2),pointList.get(size-1));
    }
}
