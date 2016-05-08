package com.starun.www.starun.pview;

import android.app.Activity;

import com.starun.www.starun.model.data.RunPlanData;

import java.util.ArrayList;

/**
 * Created by TPIAN on 16/5/5.
 */
public interface PlanView {
    /**
     * 获取实现了PlanView接口的Activity
     * @return 一个Activity对象
     */
    public abstract Activity getActivity();

    void onLoadPlanResult(ArrayList<RunPlanData> planDatas);

}
