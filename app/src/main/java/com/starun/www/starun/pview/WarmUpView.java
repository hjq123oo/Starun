package com.starun.www.starun.pview;

import android.app.Activity;
import android.view.View;

import com.starun.www.starun.model.Exercise;
import com.starun.www.starun.model.data.RunRecord;

/**
 * Created by hjq on 2016/3/28.
 */
public interface WarmUpView {
    /**
     * 获取实现了WarmUpView接口的Activity
     * @return 一个Activity对象
     */
    public abstract Activity getActivity();

    public abstract void onUpdateExerciseInfo(Exercise e);
    public abstract void onExerciseStart();
    public abstract void onExervisePause();
    public abstract void onExerciseStop();
}
