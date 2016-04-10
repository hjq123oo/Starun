package com.starun.www.starun.pview;

import android.view.View;

import com.starun.www.starun.model.Exercise;
import com.starun.www.starun.model.RunRecord;

/**
 * Created by hjq on 2016/3/28.
 */
public interface WarmUpView {
    void onUpdateExerciseInfo(Exercise e);
    void onExerciseFinished(Exercise e);
    void insertRunRecord(RunRecord runRecord);
    void onSetFloatBtnType(int type);
    void onSetDisplayView(View displayView);
}
