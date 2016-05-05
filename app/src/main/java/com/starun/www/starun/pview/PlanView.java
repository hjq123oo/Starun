package com.starun.www.starun.pview;

import com.starun.www.starun.model.data.RunPlanData;

import java.util.ArrayList;

/**
 * Created by TPIAN on 16/5/5.
 */
public interface PlanView {

    void onLoadPlanResult(ArrayList<RunPlanData> planDatas);

}
