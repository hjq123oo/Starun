package com.starun.www.starun.pview;

import android.app.Activity;
import android.widget.Chronometer;

import com.starun.www.starun.model.IRun;

/**
 * Created by xiaoxue on 2016/4/10.
 */
public interface RunView{
    /**
     * 获取实现了RunView接口的Activity
     * @return 一个Activity对象
     */
    public abstract Activity getActivity();

    /**
     * 获取计时器控件
     * @return 一个Chronometer对象
     */
    public abstract Chronometer getChronometer();

    public abstract void onUpdateRunInfo(IRun run);
}