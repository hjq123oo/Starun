package com.starun.www.starun.model.data;

/**
 * Created by hjq on 2016/4/10.
 */
public class RunRecord {
    private int runRecordId;//跑步记录id
    private long startTime;//开始时间
    private long endTime;//结束时间
    private double kilometer;//公里数
    private double avgSpeed;//平均速度
    private String traceEntity;//轨迹实体
    private int usrId;//用户id

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

    public double getKilometer() {
        return kilometer;
    }

    public void setKilometer(double kilometer) {
        this.kilometer = kilometer;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public String getTraceEntity() {
        return traceEntity;
    }

    public void setTraceEntity(String traceEntity) {
        this.traceEntity = traceEntity;
    }

    public int getUsrId() {
        return usrId;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }
}
