package com.starun.www.starun.model.data;

/**
 * Created by hjq on 2016/4/10.
 */
public class RunPlanData {
    private int runPlanId;//跑步计划id
    private String title;//计划标题
    private String plan;//计划内容
    private String suggestion;//建议
    private String soundPath;//语音路径

    public int getRunPlanId() {
        return runPlanId;
    }

    public void setRunPlanId(int runPlanId) {
        this.runPlanId = runPlanId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }
}
