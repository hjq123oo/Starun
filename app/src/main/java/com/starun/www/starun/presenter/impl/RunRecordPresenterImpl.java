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
    private RunRecordDao runRecordDao;

    public RunRecordPresenterImpl(RunRecordView runRecordView){
        this.runRecordView = runRecordView;
        runRecordDao = new RunRecordDao(runRecordView.getActivity());
    }

    @Override
    public void loadRunRecords() {

        List<RunRecord> runRecords = runRecordDao.getRunRecords();
        runRecordView.onShowRunRecords(runRecords);
    }


}
