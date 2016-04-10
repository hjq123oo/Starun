package com.starun.www.starun.pview;

import com.starun.www.starun.model.RunRecord;

import java.util.ArrayList;

/**
 * Created by xiaoxue on 2016/4/10.
 */
public interface RunRecordView{
    void onGetRunRecordsResult(ArrayList<RunRecord> records);
}