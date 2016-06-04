package com.starun.www.starun.pview;

import android.app.Activity;

import com.starun.www.starun.model.IMain;
import com.starun.www.starun.server.data.Plan;
import com.starun.www.starun.server.data.RunTotalInfo;

/**
 * Created by hjq on 2016/5/23.
 */
public interface MainView {
    Activity getActivity();

    void onDataShow(RunTotalInfo runTotalInfo,int planStage);
}
