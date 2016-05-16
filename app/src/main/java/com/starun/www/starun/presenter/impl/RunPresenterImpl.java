package com.starun.www.starun.presenter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;

import com.starun.www.starun.dao.RunRecordDao;
import com.starun.www.starun.model.IRun;
import com.starun.www.starun.model.data.RunRecord;
import com.starun.www.starun.presenter.RunPresenter;
import com.starun.www.starun.pview.RunView;
import com.starun.www.starun.service.TraceService;

/**
 * Created by yearsj on 2016/4/7.
 */
public class RunPresenterImpl implements RunPresenter {

    private RunView runView = null;
    private MsgReceiver msgReceiver = null;
    private RunRecordDao runRecordDao = null;
    private RunRecord runRecord = new RunRecord();


    public RunPresenterImpl(RunView runView){
        this.runView = runView;
        runRecordDao = new RunRecordDao(runView.getActivity());
    }

    @Override
    public void doRunStart() {
        runRecord.setStartTime(System.currentTimeMillis()/1000);
        Intent service = new Intent(runView.getActivity(),TraceService.class);
        runView.getActivity().startService(service);
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
        runRecord.setEndTime(System.currentTimeMillis() / 1000);
        //获取计时器时间

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
        return SystemClock.elapsedRealtime()-longTime;
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
        if(null!=runRecord.getTraceEntity())
            runRecordDao.addRunRecord(runRecord);
    }
}
