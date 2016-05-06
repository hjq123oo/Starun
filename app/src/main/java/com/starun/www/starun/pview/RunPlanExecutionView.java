package com.starun.www.starun.pview;

import android.app.Activity;
import android.widget.Chronometer;

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
}
