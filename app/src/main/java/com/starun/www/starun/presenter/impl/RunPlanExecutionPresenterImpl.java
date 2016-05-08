package com.starun.www.starun.presenter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Chronometer;

import com.starun.www.starun.dao.RunRecordDao;
import com.starun.www.starun.model.IRun;
import com.starun.www.starun.model.RunPlanExecutionService;
import com.starun.www.starun.model.data.RunRecord;
import com.starun.www.starun.presenter.RunPlanExecutionPresenter;
import com.starun.www.starun.pview.RunPlanExecutionView;
import com.starun.www.starun.service.TraceService;


/**
 * Created by hjq on 2016/4/28.
 */
public class RunPlanExecutionPresenterImpl implements RunPlanExecutionPresenter {
    private RunPlanExecutionService runPlanExecutionService;

    private RunPlanExecutionView runPlanExecutionView;
    private Chronometer chronometer;

    private MsgReceiver msgReceiver = null;
    private RunRecordDao runRecordDao = null;
    private RunRecord runRecord = new RunRecord();

    public RunPlanExecutionPresenterImpl(RunPlanExecutionView runPlanExecutionView){
        this.runPlanExecutionView = runPlanExecutionView;
        chronometer = this.runPlanExecutionView.getChronometer();

        runPlanExecutionService = new RunPlanExecutionService(runPlanExecutionView.getActivity());
    }


    @Override
    public void doRunStart() {
        runPlanExecutionService.startPlan();

        if(!runPlanExecutionService.isRun()){
            //回调提示对话框
            runPlanExecutionService.getRunPlanData().getDesc();


            runPlanExecutionService.finishPlan();
        }else{
            chronometer.setOnChronometerTickListener(new MyOnChronometerTickListener());
            runRecord.setStartTime(System.currentTimeMillis()/1000);
            //Intent service = new Intent(runView.getActivity(),TraceService.class);
            //runView.getActivity().startService(service);
        }

    }

    @Override
    public void doRunPause() {
        //暂停service
        //Intent service = new Intent(runView.getActivity(),TraceService.class);
        //runView.getActivity().stopService(service);
    }

    @Override
    public void doRunStop() {
        //停止service
        runRecord.setEndTime(System.currentTimeMillis()/1000);
        //Intent service = new Intent(runView.getActivity(),TraceService.class);
        //runView.getActivity().stopService(service);
    }

    class MyOnChronometerTickListener implements Chronometer.OnChronometerTickListener{

        @Override
        public void onChronometerTick(Chronometer chronometer) {
            //获取计时器时间
            long time = 0;

            int state = runPlanExecutionService.executePlan(time);
            if(state == 1){
                //回调运动状态改变
            }else if(state == 2){
                chronometer.stop();
                runPlanExecutionService.finishPlan();

            }



        }
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

            runPlanExecutionService.getIRunPlanExecution().setKilometer(distance);
            //回调更新

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
            //runView.getActivity().registerReceiver(msgReceiver, intentFilter);
        }
    }

    /**
     * 注销广播
     */
    public void unregisterReceiver(){
        if(null!=msgReceiver){
            //runView.getActivity().unregisterReceiver(msgReceiver);
            //msgReceiver = null;
        }
    }

    @Override
    public void saveRunInfo() {
        if(null!=runRecord.getTraceEntity())
            runRecordDao.addRunRecord(runRecord);

    }
}
