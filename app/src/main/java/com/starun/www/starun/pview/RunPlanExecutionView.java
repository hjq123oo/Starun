package com.starun.www.starun.pview;

import android.app.Activity;
import android.widget.Chronometer;

import com.starun.www.starun.model.IRunPlanExecution;
import com.starun.www.starun.model.data.RunPlanData;

import java.util.List;

/**
 * Created by hjq on 2016/5/6.
 */
public interface RunPlanExecutionView {
    /**
     * 获取实现了RunPlanExecutionView接口的Activity
     * @return 一个Activity对象
     */
    public abstract Activity getActivity();

    /**
     * 获取计时器控件
     * @return 一个Chronometer对象
     */
    public abstract Chronometer getChronometer();

    /**
     * 展示建议
     * @param iRunPlanExecution 视图逻辑交互数据
     */
    public abstract void onShowTip(IRunPlanExecution iRunPlanExecution);

    /**
     * 展示提示标签界面
     * @param tagTitle 提示数据标题
     * @param tagText 提示数据文本
     */
    public abstract void onShowTag(String tagTitle,String tagText);


    /**
     * 展示选项界面
     * @param options 选项数据
     */
    public abstract void onShowOptions(List<String> options);

    /**
     * 运动开始
     */
    public abstract void onRunStart();

    /**
     * 运动暂停
     */
    public abstract void onRunPause();

    /**
     * 运动结束
     */
    public abstract void onRunStop();


    /**
     * 运动数据更新
     * @param iRunPlanExecution 视图逻辑交互数据
     */
    public abstract void onUpdateInfo(IRunPlanExecution iRunPlanExecution);


}
