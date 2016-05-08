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

    /**
     * 注册广播
     */
    void registerReceiver();

    /**
     * 注销广播
     */
    void unregisterReceiver();

    /**
     * 保存数据
     */
    void saveRunInfo();
}
