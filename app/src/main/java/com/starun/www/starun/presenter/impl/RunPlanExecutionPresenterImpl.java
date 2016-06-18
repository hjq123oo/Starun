package com.starun.www.starun.presenter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Chronometer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.dao.RunRecordDao;

import com.starun.www.starun.model.logic.RunPlanExecutionLogic;
import com.starun.www.starun.presenter.RunPlanExecutionPresenter;
import com.starun.www.starun.pview.RunPlanExecutionView;
import com.starun.www.starun.server.data.Plan;
import com.starun.www.starun.server.data.RunRecord;
import com.starun.www.starun.server.util.ConnectUtil;
import com.starun.www.starun.service.PromptToneService;
import com.starun.www.starun.service.TraceService;
import com.starun.www.starun.view.application.MyApplication;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by hjq on 2016/4/28.
 */
public class RunPlanExecutionPresenterImpl implements RunPlanExecutionPresenter {
    private RunPlanExecutionLogic runPlanExecutionLogic;

    private RunPlanExecutionView runPlanExecutionView;
    private Chronometer chronometer;

    private MsgReceiver msgReceiver = null;
    //private RunRecordDao runRecordDao = null;
    private RunRecord runRecord = new RunRecord();
    private boolean isStart = false;

    private Plan plan = null;

    private int user_id;

    int state; //跑步计划类型

    private static final int PLAN_UPLOAD = 3;
    private static final int PLAN_LOAD = 2;
    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PLAN_UPLOAD:
                    if(state == 1){
                        doRunStop();
                    }else if(state == 3){
                        runPlanExecutionView.onTagFinish();
                    }
                    break;
                case PLAN_LOAD:
                    preparePlan();
                    break;
                case SUCCESS:
                    break;
                case FAILURE:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    public RunPlanExecutionPresenterImpl(RunPlanExecutionView runPlanExecutionView){
        this.runPlanExecutionView = runPlanExecutionView;

        runPlanExecutionLogic = new RunPlanExecutionLogic(runPlanExecutionView.getActivity());

        user_id = ((MyApplication)runPlanExecutionView.getActivity().getApplication()).getUser().getUser_id();

        runRecord.setUser_id(user_id);
    }


    @Override
    public void doRunPrepare() {
        new Thread(){
            @Override
            public void run() {
                super.run();

                String userMessage = "user_id="+ user_id;
                String response = ConnectUtil.getResponse("getPlan", userMessage);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }
                if("true".equals(result)&&null!=result){
                    String msg = map.get("msg");
                    Map<String, String> msgMap = JSON.parseObject(msg, new TypeReference<Map<String, String>>() {
                    });

                    plan = JSON.parseObject(msgMap.get("plan"),new TypeReference<Plan>(){});
                    if(plan == null){
                        plan = new Plan();
                        plan.setUser_id(user_id);
                        plan.setRun_plan_id(1);
                        plan.setLesson_index(1);
                        plan.setPlan_percentage(0);
                    }
                    myHandler.sendEmptyMessage(PLAN_LOAD);
                }
                else if(result == null){

                }else if(result.equals("false")){
                    if(plan == null){
                        plan = new Plan();
                        plan.setUser_id(user_id);
                        plan.setRun_plan_id(1);
                        plan.setLesson_index(1);
                        plan.setPlan_percentage(0);
                    }
                    myHandler.sendEmptyMessage(PLAN_LOAD);
                }

            }
        }.start();

    }


    private void preparePlan(){
        state = runPlanExecutionLogic.preparePlan(plan);
        if(state == 1){
            runPlanExecutionLogic.startRun();
            //回调跑步界面

            doRunStart();
        }else if(state == 2){
            //回调选项对话框
            runPlanExecutionView.onShowOptions(runPlanExecutionLogic.getRunOptions());
        }else if(state == 3){
            //回调提示对话框
            runPlanExecutionView.onShowTag(runPlanExecutionLogic.getRunPlanData().getTitle(),runPlanExecutionLogic.getRunPlanData().getDesc());
        }
    }


    private void uploadPlan(final Plan uploadPlan){
        new Thread(){
            @Override
            public void run() {
                super.run();
                Plan newPlan = new Plan();

                newPlan.setUser_id(user_id);
                newPlan.setRun_plan_id(uploadPlan.getRun_plan_id());
                newPlan.setLesson_index(uploadPlan.getLesson_index());
                newPlan.setPlan_percentage(uploadPlan.getPlan_percentage());

                String message = JSON.toJSONString(newPlan);
                String response = ConnectUtil.getResponsePostJson("updatePlan", message);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");

                }
                if(null!=result&&"true".equals(result)){
                    myHandler.sendEmptyMessage(PLAN_UPLOAD);
                }
                else{

                }
            }
        }.start();
    }

    @Override
    public void doRunOptionChose(int position) {
        runPlanExecutionLogic.chooseOption(position);
        runPlanExecutionLogic.startRun();


        doRunStart();
    }


    @Override
    public void doLoadTip() {
        runPlanExecutionView.onShowTip(runPlanExecutionLogic.getIRunPlanExecution());
    }

    @Override
    public void doRunStart() {
        if(isStart){
            Intent service = new Intent(runPlanExecutionView.getActivity(),TraceService.class);
            runPlanExecutionView.getActivity().startService(service);
        }else{
            isStart = true;
            chronometer = runPlanExecutionView.getChronometer();
            chronometer.setOnChronometerTickListener(new MyOnChronometerTickListener());
            runRecord.setStartTime(System.currentTimeMillis());
            Intent service = new Intent(runPlanExecutionView.getActivity(),TraceService.class);
            runPlanExecutionView.getActivity().startService(service);
        }

        //回调跑步开始
        runPlanExecutionView.onRunStart();
    }

    @Override
    public void doRunPause() {
        //暂停service
        Intent service = new Intent(runPlanExecutionView.getActivity(),TraceService.class);
        runPlanExecutionView.getActivity().stopService(service);

        //回调跑步暂停
        runPlanExecutionView.onRunPause();
    }

    @Override
    public void doRunStop() {
        //停止service
        runRecord.setEndTime(System.currentTimeMillis());
        runRecord.setRunTime(convertStrTimeToLong(chronometer.getText().toString()));
        Intent service = new Intent(runPlanExecutionView.getActivity(),TraceService.class);
        runPlanExecutionView.getActivity().stopService(service);

        if(((MyApplication)runPlanExecutionView.getActivity().getApplicationContext()).getSetting().isHasVoice()){
            Intent intent = new Intent();
            intent.setClass(runPlanExecutionView.getActivity(), PromptToneService.class);
            intent.putExtra("MSG", PromptToneService.ENDRUN);
            runPlanExecutionView.getActivity().startService(intent);
        }
        saveRunInfo();

        //回调跑步结束
        runPlanExecutionView.onRunStop();
    }

    @Override
    public void doTagFinish() {
        Plan uplpadPlan = runPlanExecutionLogic.finishPlan();
        uploadPlan(uplpadPlan);
    }


    class MyOnChronometerTickListener implements Chronometer.OnChronometerTickListener{

        @Override
        public void onChronometerTick(Chronometer chronometer) {
            //获取计时器时间
            long time = convertStrTimeToLong(chronometer.getText().toString());

            int state = runPlanExecutionLogic.executePlan(time,runRecord.getKilometer());
            if(state == 0){
                //时间改变
                runPlanExecutionView.onUpdateInfo(runPlanExecutionLogic.getIRunPlanExecution());
            }else if(state == 1){
                //回调运动状态改变
                runPlanExecutionView.onUpdateInfo(runPlanExecutionLogic.getIRunPlanExecution());
            }else if(state == 2){
                //回调运动结束
                Plan plan = runPlanExecutionLogic.finishPlan();
                uploadPlan(plan);

            }



        }
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
            distance = Math.round(distance * 100) * 0.01d;
            String entity = intent.getStringExtra("entityName");
            runRecord.setKilometer(distance);
            runRecord.setTraceEntity(entity);

            runPlanExecutionLogic.getIRunPlanExecution().setKilometer(distance);
            //回调更新
            runPlanExecutionView.onUpdateInfo(runPlanExecutionLogic.getIRunPlanExecution());
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
            runPlanExecutionView.getActivity().registerReceiver(msgReceiver, intentFilter);
        }
    }

    /**
     * 注销广播
     */
    public void unregisterReceiver(){
        if(null!=msgReceiver){
            runPlanExecutionView.getActivity().unregisterReceiver(msgReceiver);
            msgReceiver = null;
        }
    }


    private void saveRunInfo() {

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
