package com.starun.www.starun.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.dao.RunRecordDao;
import com.starun.www.starun.presenter.RunRecordPresenter;
import com.starun.www.starun.pview.RunRecordView;
import com.starun.www.starun.server.data.RunRecord;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.util.ConnectUtil;
import com.starun.www.starun.view.application.MyApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hjq on 2016/4/13.
 */
public class RunRecordPresenterImpl implements RunRecordPresenter{
    private RunRecordView runRecordView;
    //private RunRecordDao runRecordDao;

    private List<RunRecord> runRecords;

    private int user_id;

    //加载一次记录的数量
    private static final int RECORD_LOAD_NUM = 10;

    //下一个记录索引
    private int nextRecordBegin = 0;


    private static final int FAILURE = 0;
    private static final int LOAD_INIT = 1;
    private static final int LOAD_UPDATE = 2;

    private boolean isInit = true;

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //判断发送的消息
                case LOAD_UPDATE:
                    runRecordView.onUpdateRunRecords();
                    break;

                case LOAD_INIT:
                    runRecordView.onInitRunRecords();
                    break;

                case FAILURE:
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    public RunRecordPresenterImpl(RunRecordView runRecordView){
        this.runRecordView = runRecordView;

        user_id = ((MyApplication) runRecordView.getActivity().getApplication()).getUser().getUser_id();

       // runRecordDao = new RunRecordDao(runRecordView.getActivity());
    }

    @Override
    public void doRunRecordsInit() {
        runRecords = new ArrayList<RunRecord>();
        runRecordView.onShowRunRecords(runRecords);
        doRunRecordsLoad();
    }


    @Override
    public void doRunRecordsLoad() {
        new Thread(){
            @Override
            public void run() {
                super.run();

                String message = "user_id="+user_id+"&begin="+nextRecordBegin+"&end="+(nextRecordBegin+RECORD_LOAD_NUM);

                String response =  ConnectUtil.getResponse("getRunRecords", message);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }
                if("true".equals(result)&&null!=result){
                    String msg = map.get("msg");
                    JSONObject obj = JSON.parseObject(msg);
                    JSONArray arr = JSON.parseArray(obj.getJSONObject("records").getString("resultarr"));


                    for(int i=0;i<arr.size();i++){
                        JSONArray dataArr = JSON.parseArray(arr.getString(i));
                        RunRecord runRecord = new RunRecord();

                        runRecord.setRunRecordId((int) dataArr.get(0));
                        runRecord.setUser_id((int) dataArr.get(1));
                        runRecord.setStartTime((Long)dataArr.get(2));
                        runRecord.setEndTime((Long)dataArr.get(3));
                        runRecord.setRunTime((Long.valueOf((int) dataArr.get(4))));
                        runRecord.setKilometer((((BigDecimal)dataArr.get(5)).doubleValue()));
                        runRecord.setTraceEntity((String) dataArr.get(6));
                        runRecords.add(runRecord);
                        nextRecordBegin++;
                    }

                    if(isInit){
                        myHandler.sendEmptyMessage(LOAD_INIT);
                        isInit = false;
                    }else{
                        myHandler.sendEmptyMessage(LOAD_UPDATE);
                    }
                }
                else if(result == null){
                    myHandler.sendEmptyMessage(FAILURE);
                }else if(result.equals("false")){
                    if(isInit){
                        myHandler.sendEmptyMessage(LOAD_INIT);
                        isInit = false;
                    }else{
                        myHandler.sendEmptyMessage(LOAD_UPDATE);
                    }
                }
            }
        }.start();

    }




}
