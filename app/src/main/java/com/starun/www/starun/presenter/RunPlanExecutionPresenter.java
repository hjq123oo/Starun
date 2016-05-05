package com.starun.www.starun.presenter;

/**
 * Created by hjq on 2016/4/28.
 */
public interface RunPlanExecutionPresenter {
    /**
     * 开始跑步计划
     */
    void doRunStart();

    /**
     * 暂停跑步计划
     */
    void doRunPause();

    /**
     * 停止跑步计划
     */
    void doRunStop();

}
