package com.starun.www.starun.model;

import android.content.Context;

import com.starun.www.starun.dao.RunPlanDao;
import com.starun.www.starun.dao.RunPlanSharedPreferences;
import com.starun.www.starun.model.data.RunPlanData;
import com.starun.www.starun.presenter.RunPlanExecutionPresenter;
import com.starun.www.starun.presenter.RunPlanPresenter;

import java.util.List;
import java.util.Map;


/**
 * Created by hjq on 2016/4/28.
 */
public class RunPlanExecutionService {
    private RunPlanDao runPlanDao;
    private RunPlanSharedPreferences runPlanSharedPreferences;

    private RunPlanData runPlanData;

    private String[] movements; //解析计划的运动数组
    private int curMovementIndex; //当前运动索引

    String[] movement; //当前运动
    private long moveTime; //运动所需时间
    private long lastTime; //上次运动改变的时间

    private IRunPlanExecution iRunPlanExecution;



    public RunPlanExecutionService(Context context) {
        runPlanDao = new RunPlanDao(context);
        runPlanSharedPreferences = new RunPlanSharedPreferences(context);


    }




    /**
     * 获取跑步计划类型
     * @return true：当前计划代表跑步；false：当前计划代表提示文本
     */
    public boolean isRun(){
        if(runPlanData.getTagIndex() == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 开始计划
     */
    public void startPlan() {
        Map<String, Object> map = runPlanSharedPreferences.getPlanSchedule();
        int runPlanId = (int) map.get("runPlanId");
        runPlanData = runPlanDao.getRunPlanData(runPlanId);

        //当前计划为显示提示文本
        if(runPlanData.getTagIndex() == 0){
            return;
        }


        //加载跑步计划
        int lessonIndex = (int) map.get("lessonIndex");
        String lessonPlan = runPlanData.getLessonPlan(lessonIndex);
        movements = lessonPlan.split(";");
        curMovementIndex = 0;
        iRunPlanExecution.setSuggestion(runPlanData.getDesc());

        movement = movements[curMovementIndex].split(",");

        String moveState = movement[0];
        String str = movement[1];

        lastTime = 0;
        if(moveState.equals("km")){
            iRunPlanExecution.setMovementState("跑步"+str+"公里");
        }else if(moveState.equals("run")){
            iRunPlanExecution.setMovementState("跑步"+str+"分钟");
        }else if(moveState.equals("walk")){
            iRunPlanExecution.setMovementState("行走"+str+"分钟");
        }


        moveTime = Integer.parseInt(str)*60*1000;
    }


    /**
     * 判断当前运动计算时间或者计算路程
     * @return true:计算时间;false:计算路程
     */
    public boolean useTimeOrKm(){
        String moveState = movement[0];

        if(moveState.equals("run")){
            return true;
        }else if(moveState.equals("walk")){
            return true;
        }else if(moveState.equals("km")){
            return false;
        }

        return true;
    }



    /**
     * 执行计划
     * @param time 计划已执行的时间
     * @return 运动状态 0:运动状态未改变;1:运动状态改变;2:运动结束
     */
    public int executePlan(long time){
        if(time - lastTime >= moveTime){
            lastTime = lastTime+moveTime;
            curMovementIndex++;

            //运动结束
            if(curMovementIndex >= movements.length){
                return 2;
            }

            //改变运动状态
            movement = movements[curMovementIndex].split(",");

            String moveState = movement[0];
            String str = movement[1];

            if(moveState.equals("run")){
                iRunPlanExecution.setMovementState("跑步"+str+"分钟");
            }else if(moveState.equals("walk")){
                iRunPlanExecution.setMovementState("行走"+str+"分钟");
            }

            moveTime = Integer.parseInt(str)*60*1000;

            return 1;
        }

        return 0;

    }

    public RunPlanData getRunPlanData() {
        return runPlanData;
    }

    public IRunPlanExecution getIRunPlanExecution(){
        return iRunPlanExecution;
    }


    /**
     * 完成计划
     */
    public void finishPlan(){
        Map<String, Object> map = runPlanSharedPreferences.getPlanSchedule();
        int runPlanId = (int)map.get("runPlanId");
        int lessonIndex = (int)map.get("lessonIndex");
        float planPercentage = (float)map.get("planPercentage");

        //该跑步计划为提示文本时,存储下一个跑步计划id
        if(runPlanData.getTagIndex() != 0){
            runPlanSharedPreferences.setPlanSchedule(runPlanId+1,1,planPercentage);
        }else{
            //跑步计划执行到该周第三课时,存储下一个跑步计划id;否则,存储下一个跑步课程
            if(lessonIndex==3){
                runPlanSharedPreferences.setPlanSchedule(runPlanId+1,1,calPlanPercentage(runPlanData.getWeekIndex(),lessonIndex));
            }else{
                runPlanSharedPreferences.setPlanSchedule(runPlanId,lessonIndex+1,calPlanPercentage(runPlanData.getWeekIndex(),lessonIndex));
            }
        }

    }

    /**
     * 计算计划进度百分比
     * @param weekIndex 第几周
     * @param lessonIndex 第几课
     * @return 进度百分比
     */
    private float calPlanPercentage(int weekIndex,int lessonIndex){
        return ((float)((runPlanData.getWeekIndex()-1)*3+lessonIndex))/(13*3);
    }
}
