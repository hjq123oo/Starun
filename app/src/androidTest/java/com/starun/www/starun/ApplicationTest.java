package com.starun.www.starun;

import android.app.Activity;
import android.app.Application;
import android.test.ApplicationTestCase;
import android.widget.Chronometer;

import com.starun.www.starun.model.IRunPlanExecution;
import com.starun.www.starun.model.logic.RunPlanExecutionLogic;
import com.starun.www.starun.presenter.RunPlanExecutionPresenter;
import com.starun.www.starun.presenter.impl.RunPlanExecutionPresenterImpl;
import com.starun.www.starun.pview.RunPlanExecutionView;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.view.application.MyApplication;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

}