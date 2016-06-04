package com.starun.www.starun.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.presenter.MainPresenter;
import com.starun.www.starun.pview.MainView;
import com.starun.www.starun.server.data.RunTotalInfo;
import com.starun.www.starun.server.util.ConnectUtil;
import com.starun.www.starun.view.application.MyApplication;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by hjq on 2016/6/5.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;

    private int user_id;

    private RunTotalInfo runTotalInfo;

    private int planStage;


    private static final int FAILURE = 0;
    private static final int SUCCESS = 1;

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //判断发送的消息
                case FAILURE:
                    break;
                case SUCCESS:
                    mainView.onDataShow(runTotalInfo, planStage);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
        user_id = ((MyApplication)mainView.getActivity().getApplication()).getUser().getUser_id();
    }

    @Override
    public void doDataLoad() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String message = "user_id="+user_id;
                String response = ConnectUtil.getResponse("detailOfFriend", message);
                String result = null;
                Map<String, String> map = JSON.parseObject(response, new TypeReference<Map<String, String>>() {
                });

                if(null!=map){
                    result= map.get("result");
                }
                if("true".equals(result)&&null!=result){
                    String msg = map.get("msg");
                    runTotalInfo = JSON.parseObject(msg,new TypeReference<RunTotalInfo>(){});

                    planStage = getPlanStageByPlanPercentage(runTotalInfo.getPlan_percentage());

                    myHandler.sendEmptyMessage(SUCCESS);
                }
                else{
                    myHandler.sendEmptyMessage(FAILURE);
                }
            }
        }.start();
    }

    private int getPlanStageByPlanPercentage(double planPercentage){

        BigDecimal percentage = new BigDecimal(Double.toString(planPercentage));
        int lessons = (percentage.multiply(new BigDecimal("39")).divide(new BigDecimal("1"),0,BigDecimal.ROUND_HALF_UP).intValue());
        return (lessons)/3 + 1;
    }
}


