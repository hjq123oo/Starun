package com.starun.www.starun.presenter;

import com.starun.www.starun.model.data.RunRecord;
import com.starun.www.starun.pview.RunRecordView;

/**
 * Created by hjq on 2016/4/13.
 */
public interface RunRecordPresenter {
    /**
     *加载所有跑步记录
     */
    public abstract void loadRunRecords();
}
