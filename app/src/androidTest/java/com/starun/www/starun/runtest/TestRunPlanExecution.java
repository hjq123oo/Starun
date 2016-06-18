package com.starun.www.starun.runtest;

import android.app.Activity;
import android.test.AndroidTestCase;
import android.widget.Chronometer;

import com.starun.www.starun.dao.RunPlanSharedPreferences;
import com.starun.www.starun.model.IRunPlanExecution;
import com.starun.www.starun.model.logic.RunPlanExecutionLogic;
import com.starun.www.starun.presenter.RunPlanExecutionPresenter;
import com.starun.www.starun.presenter.impl.RunPlanExecutionPresenterImpl;
import com.starun.www.starun.pview.RunPlanExecutionView;
import com.starun.www.starun.server.data.Plan;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.view.application.MyApplication;

import java.util.List;

/**
 * Created by hjq on 2016/5/19.
 */
public class TestRunPlanExecution extends AndroidTestCase {

    public void testRunPlanLogic() {
        RunPlanExecutionLogic logic = new RunPlanExecutionLogic(this.getContext());
        Plan plan = new Plan();
        plan.setUser_id(1);
        plan.setRun_plan_id(2);
        plan.setLesson_index(1);
        plan.setPlan_percentage(0);
        logic.preparePlan(plan);

        logic.startRun();

        logic.executePlan(1000, 1.0);

        logic.executePlan(60000, 2.0);

        logic.executePlan(180000,2.0);

        logic.executePlan(1440000, 3.0);

        Plan replan = logic.finishPlan();

        System.out.print("下一个计划id:" + replan.getRun_plan_id());

        System.out.print("下一课:" + replan.getLesson_index());
    }
}
