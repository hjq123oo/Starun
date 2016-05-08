package com.starun.www.starun.model;

import android.content.Context;

import com.starun.www.starun.dao.WarmUpDao;
import com.starun.www.starun.model.data.WarmUpData;

import java.util.List;

/**
 * Created by xiaoxue on 2016/4/10.
 */
public class WarmUpService {
    public enum State{
        START,
        PAUSE,
        STOP
    }

    public static final int PROGRESS_MAX = 10;

    private State state;
    private int progress;//当前热身进度
    private WarmUpData warmUpData;//当前热身需用的数据


    private int curWarmUpDatasIndex;
    private WarmUpDao warmUpDao;
    private List<WarmUpData> warmUpDatas;

    public WarmUpService(Context context) {
        warmUpDao = new WarmUpDao(context);
        warmUpDatas = warmUpDao.getWarmUpDatas();

        start();
        progress = 0;
        curWarmUpDatasIndex = 0;
        warmUpData = warmUpDatas.get(curWarmUpDatasIndex);
    }


    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public WarmUpData getWarmUpData() {
        return warmUpData;
    }

    public void setWarmUpData(WarmUpData warmUpData) {
        this.warmUpData = warmUpData;

    }


    public void increaseProgress(){
        if(progress++ == PROGRESS_MAX){
            progress = 0;
            curWarmUpDatasIndex++;
            if(curWarmUpDatasIndex == warmUpDatas.size()){
                stop();
            }else{
                warmUpData = warmUpDatas.get(curWarmUpDatasIndex);
            }

        }
    }

    /**
     * 获取热身状态码
     * @return 0：热身结束；1：进度变化；2：热身动作变化
     */
    public int getWarmUpStatusCode(){
        if(getState() == State.STOP){
            return 0;
        }
        if(progress != 0){
            return 1;
        }else{
            return 2;
        }
    }


    public State getState() {
        return state;
    }

    public void start(){
        state = State.START;
    }

    public void pause(){
        state = State.PAUSE;
    }

    public void stop(){
        state = State.STOP;
    }

}