package com.starun.www.starun.model.data;

/**
 * Created by hjq on 2016/4/10.
 */
public class RunPlanData {
    private int runPlanId;//跑步计划id
    private String title;//计划标题
    private int tagIndex;//原则或中段检查，tagIndex为1该RunPlanData对应原则，tagIndex为2该RunPlanData对应中段检查
    private int weekIndex;//周，weekIndex表示第几周,weekIndex为0代表非跑步周计划
    private int optionIndex;//跑步选项,0表示该计划无跑步选项，1表示跑步选项，2表示跑步行走选项
    private String optionName;//选项名
    private String lessonOne;//存放第一课的计划文本描述
    private String lessonOnePlan;//存放第一课的计划数据
    private String lessonTwo;//存放第二课的计划文本描述
    private String lessonTwoPlan;//存放第二课的计划数据
    private String lessonThree;//存放第三课的计划文本描述
    private String lessonThreePlan;//存放第三课的计划数据
    private String desc;//计划其他信息，存放原则或中段检查或建议

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

    public int getTagIndex() {
        return tagIndex;
    }

    public void setTagIndex(int tagIndex) {
        this.tagIndex = tagIndex;
    }

    public int getWeekIndex() {
        return weekIndex;
    }

    public void setWeekIndex(int weekIndex) {
        this.weekIndex = weekIndex;
    }

    public int getOptionIndex() {
        return optionIndex;
    }

    public void setOptionIndex(int optionIndex) {
        this.optionIndex = optionIndex;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getLessonOne() {
        return lessonOne;
    }

    public void setLessonOne(String lessonOne) {
        this.lessonOne = lessonOne;
    }

    public String getLessonOnePlan() {
        return lessonOnePlan;
    }

    public void setLessonOnePlan(String lessonOnePlan) {
        this.lessonOnePlan = lessonOnePlan;
    }

    public String getLessonTwo() {
        return lessonTwo;
    }

    public void setLessonTwo(String lessonTwo) {
        this.lessonTwo = lessonTwo;
    }

    public String getLessonTwoPlan() {
        return lessonTwoPlan;
    }

    public void setLessonTwoPlan(String lessonTwoPlan) {
        this.lessonTwoPlan = lessonTwoPlan;
    }

    public String getLessonThree() {
        return lessonThree;
    }

    public void setLessonThree(String lessonThree) {
        this.lessonThree = lessonThree;
    }

    public String getLessonThreePlan() {
        return lessonThreePlan;
    }

    public void setLessonThreePlan(String lessonThreePlan) {
        this.lessonThreePlan = lessonThreePlan;
    }

    public String getLesson(int lessonIndex) {
        switch (lessonIndex) {
            case 1:
                return getLessonOne();
            case 2:
                return getLessonTwo();
            case 3:
                return getLessonThree();
            default:
                return null;
        }
    }

    public String getLessonPlan(int lessonIndex) {
        switch (lessonIndex) {
            case 1:
                return getLessonOnePlan();
            case 2:
                return getLessonTwoPlan();
            case 3:
                return getLessonThreePlan();
            default:
                return null;
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
