package com.starun.www.starun.presenter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.dao.RunRecordDao;
import com.starun.www.starun.model.IRun;
import com.starun.www.starun.presenter.RunPresenter;
import com.starun.www.starun.pview.RunView;
import com.starun.www.starun.server.data.RunRecord;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.util.ConnectUtil;
import com.starun.www.starun.service.PromptToneService;
import com.starun.www.starun.service.TraceService;
import com.starun.www.starun.view.application.MyApplication;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yearsj on 2016/4/7.
 */
public class RunPresenterImpl implements RunPresenter {

    private RunView runView = null;
    private MsgReceiver msgReceiver = null;
    //private RunRecordDao runRecordDao = null;
    private RunRecord runRecord;
    private boolean isStart = false;

    private int user_id;

    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    break;
                case FAILURE:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public RunPresenterImpl(RunView runView){
        this.runView = runView;
        runRecord = new RunRecord();
        user_id = ((MyApplication)runView.getActivity().getApplication()).getUser().getUser_id();
        runRecord.setUser_id(user_id);
        //runRecordDao = new RunRecordDao(runView.getActivity());
    }

    @Override
    public void doRunStart() {
        if(isStart){
            Intent service = new Intent(runView.getActivity(),TraceService.class);
            runView.getActivity().startService(service);
        }else{
            isStart = true;
            runRecord.setStartTime(System.currentTimeMillis());
            Intent service = new Intent(runView.getActivity(),TraceService.class);
            runView.getActivity().startService(service);
        }


    }

    @Override
    public void doRunPause() {
        //暂停service
        Intent service = new Intent(runView.getActivity(),TraceService.class);
        runView.getActivity().stopService(service);
    }

    @Override
    public void doRunStop() {
        //停止service
        runRecord.setEndTime(System.currentTimeMillis());
        //获取计时器时间
        if(((MyApplication)runView.getActivity().getApplicationContext()).getSetting().isHasVoice()){
            Intent intent = new Intent();
            intent.setClass(runView.getActivity(), PromptToneService.class);
            intent.putExtra("MSG", PromptToneService.ENDRUN);
            runView.getActivity().startService(intent);
        }

        runRecord.setRunTime(convertStrTimeToLong(runView.getChronometer().getText().toString()));
        Intent service = new Intent(runView.getActivity(),TraceService.class);
        runView.getActivity().stopService(service);
    }

    private long convertStrTimeToLong(String strTime) {
        // TODO Auto-generated method stub
        String []timeArry=strTime.split(":");
        long longTime=0;
        if (timeArry.length==2) {//如果时间是MM:SS格式
            longTime=Integer.parseInt(timeArry[0])*1000*60+Integer.parseInt(timeArry[1])*1000;
        }else if (timeArry.length==3){//如果时间是HH:MM:SS格式
            longTime=Integer.parseInt(timeArry[0])*1000*60*60+Integer.parseInt(timeArry[1]) *1000*60+Integer.parseInt(timeArry[0])*1000;
        }
        return longTime;
    }

    /**
     * 广播接收器
     */
    public class MsgReceiver extends BroadcastReceiver {
        public MsgReceiver() {
        }
        @Override
        public void onReceive(Context context, Intent intent) {
            //拿到进度，更新UI
            double distance = intent.getDoubleExtra("distance", 0) / 1000;
            //BigDecimal bigDecimal =  new BigDecimal(distance);
            //distance =  bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            distance = Math.round(distance * 100) * 0.01d;
            String entity = intent.getStringExtra("entityName");
            runRecord.setKilometer(distance);
            runRecord.setTraceEntity(entity);

            IRun run = new IRun();
            run.setKilometer(distance);
            runView.onUpdateRunInfo(run);
        }
    }

    /**
     * 注册广播
     */
    public void registerReceiver(){
        if(null == msgReceiver){
            msgReceiver = new MsgReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.starun.www.starun.DISTANCE");
            runView.getActivity().registerReceiver(msgReceiver, intentFilter);
        }
    }

    /**
     * 注销广播
     */
    public void unregisterReceiver(){
        if(null!=msgReceiver){
            runView.getActivity().unregisterReceiver(msgReceiver);
            msgReceiver = null;
        }
    }

    @Override
    public void saveRunInfo() {
        if(runRecord.getTraceEntity() != null){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    String message;
                    Map<String,Object> sendMap = new HashMap<String, Object>();
                    sendMap.put("user_id",runRecord.getUser_id()+"");
                    sendMap.put("startTime",runRecord.getStartTime()+"");
                    sendMap.put("endTime",runRecord.getEndTime() + "");
                    sendMap.put("runTime",runRecord.getRunTime());
                    sendMap.put("kilometer",runRecord.getKilometer());
                    sendMap.put("traceEntity",runRecord.getTraceEntity());
                    sendMap.put("traceEntity","entity");
                    message = JSON.toJSONString(sendMap);
                    String response =  ConnectUtil.getResponsePostJson("saveRunRecord", message);
                    String result = null;
                    Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                    });

                    if(null!=map){
                        result= map.get("result");
                    }
                    if("true".equals(result)&&null!=result){
                        myHandler.sendEmptyMessage(SUCCESS);
                    }
                    else{
                        myHandler.sendEmptyMessage(FAILURE);
                    }
                }
            }.start();
        }


            //runRecordDao.addRunRecord(runRecord);
    }
}
