package com.starun.www.starun.presenter;

/**
 * Created by hjq on 2016/4/28.
 */
public interface RunPlanExecutionPresenter {
    /**
     * 准备跑步
     */
    void doRunPrepare();

    /**
     * 选择跑步选项
     */
    void doRunOptionChose(int position);

    /**
     *用户已了解Tag
     */
    void doTagFinish();

    /**
     * 加载建议
     */
    void doLoadTip();

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



}
