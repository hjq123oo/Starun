package com.starun.www.starun.model.data;

/**
 * Created by hjq on 2016/4/10.
 */
public class RunRecord {
    private int runRecordId;//跑步记录id
    private long startTime;//开始时间
    private long endTime;//结束时间
    private long runTime;//跑步总共用时
    private double kilometer;//公里数
    private String traceEntity;//轨迹实体

    public String getTraceEntity() {
        return traceEntity;
    }

    public void setTraceEntity(String traceEntity) {
        this.traceEntity = traceEntity;
    }

    public int getRunRecordId() {
        return runRecordId;
    }

    public void setRunRecordId(int runRecordId) {
        this.runRecordId = runRecordId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }

    public double getKilometer() {
        return kilometer;
    }

    public void setKilometer(double kilometer) {
        this.kilometer = kilometer;
    }
}
