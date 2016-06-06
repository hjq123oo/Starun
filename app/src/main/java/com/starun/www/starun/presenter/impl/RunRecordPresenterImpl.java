package com.starun.www.starun.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.dao.RunRecordDao;
import com.starun.www.starun.presenter.RunRecordPresenter;
import com.starun.www.starun.pview.RunRecordView;
import com.starun.www.starun.server.data.RunRecord;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.util.ConnectUtil;
import com.starun.www.starun.view.application.MyApplication;

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
    private static final int SUCCESS = 1;



    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //判断发送的消息
                case SUCCESS:
                    runRecordView.onShowRunRecords(runRecords);
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

                Map<String,Object> sendMap = new HashMap<String, Object>();

                sendMap.put("user_id",user_id);
                sendMap.put("begin",nextRecordBegin);
                sendMap.put("end",nextRecordBegin+RECORD_LOAD_NUM-1);

                String message = JSON.toJSONString(sendMap);
                String response =  ConnectUtil.getResponse("getRunRecords ", message);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }
                if("true".equals(result)&&null!=result){
                    String msg = map.get("msg");

                    List<RunRecord> newRunRecords = JSON.parseObject(msg, new TypeReference<List<RunRecord>>() {
                    });

                    for(RunRecord runRecord : newRunRecords){
                        runRecords.add(runRecord);
                        nextRecordBegin++;
                    }


                    myHandler.sendEmptyMessage(SUCCESS);
                }
                else{
                    myHandler.sendEmptyMessage(FAILURE);
                }
            }
        }.start();

    }




}
