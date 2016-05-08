package com.starun.www.starun.model;

/**
 * Created by hjq on 2016/5/6.
 * 该Model对应RunPlanExecutionView展示的数据
 */
public class IRunPlanExecution {
    private String movementState;//运动状态
    private double kilometer;//已跑的公里数
    private String suggestion;//跑步建议

    public String getMovementState() {
        return movementState;
    }

    public void setMovementState(String movementState) {
        this.movementState = movementState;
    }

    public double getKilometer() {
        return kilometer;
    }

    public void setKilometer(double kilometer) {
        this.kilometer = kilometer;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
