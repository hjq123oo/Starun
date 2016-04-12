package com.starun.www.starun.daotest;

import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.starun.www.starun.dao.RunRecordDao;
import com.starun.www.starun.model.data.RunRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by hjq on 2016/4/12.
 * 测试RunRecordDao类的所有方法
 */
public class RunRecordDaoTest extends AndroidTestCase {
    public void testAddRunRecord() throws Exception {
        RunRecordDao runRecordDao = new RunRecordDao(getContext());

        RunRecord runRecord = new RunRecord();

        Date date = new Date();
        runRecord.setStartTime(date.getTime());
        runRecord.setEndTime(date.getTime());
        runRecord.setKilometer(1.55);
        runRecord.setAvgSpeed(10);
        runRecord.setTraceEntity("trace_entity1");
        runRecord.setUsrId(1);

        runRecordDao.addRunRecord(runRecord);
    }

    public void testDeleteRunRecord() throws Exception {
        RunRecordDao runRecordDao = new RunRecordDao(getContext());
        runRecordDao.deleteRunRecord(2);
    }

    public void testUpdateRunRecord() throws Exception {
        RunRecordDao runRecordDao = new RunRecordDao(getContext());

        RunRecord runRecord = new RunRecord();

        Date date = new Date();
        runRecord.setRunRecordId(3);
        runRecord.setStartTime(date.getTime());
        runRecord.setEndTime(date.getTime());
        runRecord.setKilometer(1.55);
        runRecord.setAvgSpeed(10);
        runRecord.setTraceEntity("trace_entity1");
        runRecord.setUsrId(2);

        runRecordDao.updateRunRecord(runRecord);
    }

    public void testGetRunRecord() throws Exception {
        RunRecordDao runRecordDao = new RunRecordDao(getContext());
        RunRecord runRecord = runRecordDao.getRunRecord(3);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = new Date(runRecord.getStartTime());
        Date endTime = new Date(runRecord.getEndTime());
        Log.d("testGetRunRecord",
                runRecord.getRunRecordId() + " " + sdf.format(startTime) + " " + sdf.format(endTime) + " " +
                        runRecord.getKilometer() + " " + runRecord.getAvgSpeed() + " " + runRecord.getTraceEntity() + " " +
                        runRecord.getUsrId());
    }

    public void testGetRunRecordList() throws Exception {
        RunRecordDao runRecordDao = new RunRecordDao(getContext());
        List<RunRecord> list = runRecordDao.getRunRecordList();

        int size = list.size();
        for(int i=0;i<size;i++){
            RunRecord runRecord = list.get(i);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = new Date(runRecord.getStartTime());
            Date endTime = new Date(runRecord.getEndTime());
            Log.d("testGetRunRecordList"+i,
                    runRecord.getRunRecordId() + " " + sdf.format(startTime)+ " " + sdf.format(endTime) + " " +
                            runRecord.getKilometer() + " " + runRecord.getAvgSpeed() + " " + runRecord.getTraceEntity() + " " +
                            runRecord.getUsrId());
        }



    }
}
