package com.starun.www.starun.presenter.impl;

import com.starun.www.starun.dao.RunRecordDao;
import com.starun.www.starun.model.data.RunRecord;
import com.starun.www.starun.presenter.RunRecordPresenter;
import com.starun.www.starun.pview.RunRecordView;

import java.util.List;

/**
 * Created by hjq on 2016/4/13.
 */
public class RunRecordPresenterImpl implements RunRecordPresenter{
    private RunRecordView runRecordView;

    public RunRecordPresenterImpl(RunRecordView runRecordView){
        this.runRecordView = runRecordView;
    }

    @Override
    public void loadRunRecords() {
        RunRecordDao runRecordDao = new RunRecordDao(runRecordView.getActivity());
        List<RunRecord> runRecords = runRecordDao.getRunRecordList();
        runRecordView.onShowRunRecords(runRecords);
    }
}
