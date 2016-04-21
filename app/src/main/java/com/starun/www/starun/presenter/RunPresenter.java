package com.starun.www.starun.presenter;

import com.starun.www.starun.model.IRun;

/**
 * Created by yearsj on 2016/4/7.
 */
public interface RunPresenter {
    /**
     * 开始跑步
     */
    void doRunStart();

    /**
     * 暂停跑步
     */
    void doRunPause();

    /**
     * 停止跑步
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
