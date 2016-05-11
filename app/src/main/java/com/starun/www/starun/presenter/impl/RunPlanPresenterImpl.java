package com.starun.www.starun.presenter.impl;

import com.starun.www.starun.dao.RunPlanDao;
import com.starun.www.starun.dao.RunRecordDao;
import com.starun.www.starun.model.data.RunPlanData;
import com.starun.www.starun.presenter.RunPlanPresenter;
import com.starun.www.starun.pview.PlanView;
import com.starun.www.starun.pview.RunPlanExecutionView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjq on 2016/5/5.
 */
public class RunPlanPresenterImpl implements RunPlanPresenter{

    private PlanView planView;
    private RunPlanDao runPlanDao;

    public RunPlanPresenterImpl(PlanView planView){
        this.planView = planView;
        runPlanDao = new RunPlanDao(planView.getActivity());
    }

    @Override
    public void doLoadRunPlan(int week) {
        List<RunPlanData> list = runPlanDao.getRunPlanDatasByWeek(week);
        planView.onLoadPlanResult((ArrayList)list);
    }

    @Override
    public void doLoadTag(@RUN_TAG_TYPE int type) {
        ArrayList<RunPlanData> list = new ArrayList<RunPlanData>();
        list.add(runPlanDao.getRunPlanDataByTag(type));
        planView.onLoadPlanResult(list);
    }

    @Override
    public void doLoadPrinciple() {
        ArrayList<RunPlanData> list = new ArrayList<RunPlanData>();
        list.add(runPlanDao.getRunPlanDataByTag(1));
        planView.onLoadPlanResult(list);
    }

    @Override
    public void doLoadCheck() {
        ArrayList<RunPlanData> list = new ArrayList<RunPlanData>();
        list.add(runPlanDao.getRunPlanDataByTag(2));
        planView.onLoadPlanResult(list);
    }
}
