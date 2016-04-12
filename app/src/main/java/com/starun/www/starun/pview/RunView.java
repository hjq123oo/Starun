package com.starun.www.starun.pview;

import android.app.Activity;
import android.view.View;

import com.starun.www.starun.model.Exercise;

/**
 * Created by xiaoxue on 2016/4/10.
 */
public interface RunView{
    /**
     * 获取实现了RunView接口的Activity
     * @return 一个Activity对象
     */
    public abstract Activity getActivity();

    public abstract void onUpdateExerciseInfo(Exercise e);
    public abstract void onExerciseStart();
    public abstract void onExervisePause();
    public abstract void onExerciseStop();
}