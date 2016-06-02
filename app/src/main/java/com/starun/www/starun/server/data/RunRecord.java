package com.starun.www.starun.server.data;

/**
 * Created by hjq on 2016/6/2.
 */
public class RunRecord {
    private int user_id; //用户id
    private int runRecordId;//跑步记录id
    private long startTime;//开始时间
    private long endTime;//结束时间
    private long runTime;//跑步总共用时
    private double kilometer;//公里数
    private String traceEntity;//轨迹实体

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

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
