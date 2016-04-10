package com.starun.www.starun.pview;

import android.view.View;

import com.starun.www.starun.model.Exercise;

/**
 * Created by xiaoxue on 2016/4/10.
 */
public interface RunView{
    void onUpdateExerciseInfo(Exercise e);
    void onSetFloatBtnType(int type);
    void onSetDisplayView(View displayView);
}