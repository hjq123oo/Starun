package com.starun.www.starun.pview;

import android.app.Activity;


import com.starun.www.starun.server.data.RunRecord;

import java.util.List;

/**
 * Created by xiaoxue on 2016/4/10.
 */
public interface RunRecordView{
    /**
     * 获取实现了RunRecordView接口的Activity
     * @return 一个Activity对象
     */
    public abstract Activity getActivity();


    /**
     * RunRecordPresenter回调View层显示跑步记录
     * @param records 包含所有跑步记录的List
     */
    public abstract void onShowRunRecords(List<RunRecord> records);


    /**
     * 更新跑步记录
     */
    public abstract void onUpdateRunRecords();
}