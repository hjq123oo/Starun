package com.starun.www.starun.model.logic;

import android.content.Context;
import android.content.Intent;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.starun.www.starun.dao.RunPlanDao;
import com.starun.www.starun.dao.RunPlanSharedPreferences;
import com.starun.www.starun.model.IRunPlanExecution;
import com.starun.www.starun.model.data.RunPlanData;
import com.starun.www.starun.presenter.RunPlanExecutionPresenter;
import com.starun.www.starun.presenter.RunPlanPresenter;
import com.starun.www.starun.server.data.Plan;
import com.starun.www.starun.server.util.ConnectUtil;
import com.starun.www.starun.service.PromptToneService;
import com.starun.www.starun.view.application.MyApplication;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by hjq on 2016/4/28.
 */
public class RunPlanExecutionLogic {
    Context context = null;

    private RunPlanDao runPlanDao;
    private RunPlanSharedPreferences runPlanSharedPreferences;

    private Plan plan; //服务器存储的当前plan

    private RunPlanData runPlanData;

    private String[] movements; //解析计划的运动数组
    private int curMovementIndex; //当前运动索引

    String[] movement; //当前运动
    private long moveTime; //运动所需时间
    private long lastTime; //上次运动改变的时间

    private double moveKilometer; //运动所需公里数

    private IRunPlanExecution iRunPlanExecution;


    //带选项的跑步计划列表
    List<RunPlanData> optionedRunPlanDatas = null;
    //选项名列表
    List<String>  optionList = null;

    public RunPlanExecutionLogic(Context context) {
        this.context = context;
        runPlanDao = new RunPlanDao(context);
        runPlanSharedPreferences = new RunPlanSharedPreferences(context);

    }


    public int preparePlan(Plan plan){
        this.plan = plan;


        runPlanData = runPlanDao.getRunPlanData(plan.getRun_plan_id());

        int state = getPlanState();

        if(state == 2){
            optionedRunPlanDatas = runPlanDao.getRunPlanDatasByWeek(runPlanData.getWeekIndex());

            optionList = new ArrayList<String>();

            int size = optionedRunPlanDatas.size();

            for(int i=0;i<size;i++){
                optionList.add(optionedRunPlanDatas.get(i).getOptionName());
            }

        }

        return state;


    }

    /**
     * 准备跑步计划
     * @return 跑步计划类型
     */
    public int preparePlanForLocal(){
        Map<String, Object> map = runPlanSharedPreferences.getPlanSchedule();
        int runPlanId = (int) map.get("runPlanId");
        runPlanData = runPlanDao.getRunPlanData(runPlanId);

        int state = getPlanState();

        if(state == 2){
            optionedRunPlanDatas = runPlanDao.getRunPlanDatasByWeek(runPlanData.getWeekIndex());

            optionList = new ArrayList<String>();

            int size = optionedRunPlanDatas.size();

            for(int i=0;i<size;i++){
                optionList.add(optionedRunPlanDatas.get(i).getOptionName());
            }

        }

        return state;
    }

    /**
     * 得到跑步计划类型
     * @return 1：跑步计划；2：带选项的跑步计划；3：跑步计划标签
     */
    private int getPlanState(){
        if(runPlanData.getTagIndex() == 0){
            if(runPlanData.getOptionIndex() == 0){
                return 1;
            }else{
                return 2;
            }
        }else{
            return 3;
        }
    }

    /**
     * 获取跑步选项
     * @return 选项列表
     */
    public List<String> getRunOptions(){
        return optionList;
    }

    /**
     * 加载某一选项跑步计划
     */
    public void chooseOption(int position){
        if(optionedRunPlanDatas != null){
            runPlanData = optionedRunPlanDatas.get(position);
        }

    }


    public void startRun(){
        //加载跑步计划
        int lessonIndex = plan.getLesson_index();
        String lessonPlan = runPlanData.getLessonPlan(lessonIndex);
        movements = lessonPlan.split(";");
        curMovementIndex = 0;

        iRunPlanExecution = new IRunPlanExecution();
        iRunPlanExecution.setSuggestion(runPlanData.getDesc());

        movement = movements[curMovementIndex].split(",");

        String moveState = movement[0];
        String str = movement[1];

        lastTime = 0;
        if(moveState.equals("km")){
            iRunPlanExecution.setMovementState("跑步"+str+"公里");
            moveKilometer = Double.parseDouble(str);
        }else if(moveState.equals("run")){
            iRunPlanExecution.setMovementState("跑步"+str+"分钟倒计时");
            moveTime = Integer.parseInt(str)*60*1000;
            iRunPlanExecution.setTime(moveTime);
        }else if(moveState.equals("walk")){
            iRunPlanExecution.setMovementState("行走"+str+"分钟倒计时");
            moveTime = Integer.parseInt(str)*60*1000;
            iRunPlanExecution.setTime(moveTime);

        }
    }

    /**
     * 开始跑步
     */
    public void startRunForLocal() {
        Map<String, Object> map = runPlanSharedPreferences.getPlanSchedule();

        //加载跑步计划
        int lessonIndex = (int) map.get("lessonIndex");
        String lessonPlan = runPlanData.getLessonPlan(lessonIndex);
        movements = lessonPlan.split(";");
        curMovementIndex = 0;

        iRunPlanExecution = new IRunPlanExecution();
        iRunPlanExecution.setSuggestion(runPlanData.getDesc());

        movement = movements[curMovementIndex].split(",");

        String moveState = movement[0];
        String str = movement[1];

        lastTime = 0;
        if(moveState.equals("km")){
            iRunPlanExecution.setMovementState("跑步"+str+"公里");
            moveKilometer = Double.parseDouble(str);
        }else if(moveState.equals("run")){
            iRunPlanExecution.setMovementState("跑步"+str+"分钟倒计时");
            moveTime = Integer.parseInt(str)*60*1000;
            iRunPlanExecution.setTime(moveTime);
        }else if(moveState.equals("walk")){
            iRunPlanExecution.setMovementState("行走"+str+"分钟倒计时");
            moveTime = Integer.parseInt(str)*60*1000;
            iRunPlanExecution.setTime(moveTime);

        }


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
    public int executePlan(long time,double kilometer){
        if(useTimeOrKm()){
            //超过的时间
            long restTime = time - lastTime - moveTime;

            if(restTime >= 0){
                lastTime = lastTime+moveTime;
                curMovementIndex++;

                //运动结束
                if(curMovementIndex >= movements.length){
                    if(((MyApplication)context.getApplicationContext()).getSetting().isHasVoice()){
                        Intent intent = new Intent();
                        intent.setClass(context, PromptToneService.class);
                        intent.putExtra("MSG", PromptToneService.ENDRUN);
                        context.startService(intent);
                    }
                    return 2;
                }

                //改变运动状态
                movement = movements[curMovementIndex].split(",");

                String moveState = movement[0];
                String str = movement[1];

                if(moveState.equals("run")){
                    iRunPlanExecution.setMovementState("跑步"+str+"分钟倒计时");
                    if(((MyApplication)context.getApplicationContext()).getSetting().isHasVoice()){
                        Intent intent = new Intent();
                        intent.setClass(context, PromptToneService.class);
                        intent.putExtra("MSG", PromptToneService.STARTRUN);
                        context.startService(intent);
                    }
                }else if(moveState.equals("walk")){
                    iRunPlanExecution.setMovementState("行走"+str+"分钟倒计时");
                    if(((MyApplication)context.getApplicationContext()).getSetting().isHasVoice()){
                        Intent intent = new Intent();
                        intent.setClass(context, PromptToneService.class);
                        intent.putExtra("MSG", PromptToneService.STARTWALK);
                        context.startService(intent);
                    }
                }



                moveTime = Integer.parseInt(str)*60*1000;

                iRunPlanExecution.setTime(moveTime - restTime);
                return 1;
            }else{
                iRunPlanExecution.setTime(0-restTime);
            }

            return 0;
        }else{
            if(kilometer >= moveKilometer){
                //运动结束
                if(curMovementIndex >= movements.length){
                    return 2;
                }
            }

            return 0;
        }


    }

    public RunPlanData getRunPlanData() {
        return runPlanData;
    }

    public IRunPlanExecution getIRunPlanExecution(){
        return iRunPlanExecution;
    }


    public Plan finishPlan(){

        int runPlanId = plan.getRun_plan_id();
        int lessonIndex = plan.getLesson_index();
        double planPercentage = plan.getPlan_percentage();

        //该跑步计划为提示文本时,存储下一个跑步计划id
        if(getPlanState() == 3){
            Plan rPlan = new Plan();
            rPlan.setRun_plan_id(runPlanId + 1);
            rPlan.setLesson_index(1);
            rPlan.setPlan_percentage(planPercentage);
            return rPlan;
        }else{
            //跑步计划执行到该周第三课时,存储下一个跑步计划id;否则,存储下一个跑步课程
            if(lessonIndex==3){
                //该跑步计划包含选项,跑步计划id+2
                if(getPlanState() == 2){
                    Plan rPlan = new Plan();
                    rPlan.setRun_plan_id(runPlanId + 2);
                    rPlan.setLesson_index(1);
                    rPlan.setPlan_percentage( calPlanPercentage(runPlanData.getWeekIndex(), lessonIndex));
                    return rPlan;

                }else{
                    Plan rPlan = new Plan();
                    rPlan.setRun_plan_id(runPlanId + 1);
                    rPlan.setLesson_index(1);
                    rPlan.setPlan_percentage(calPlanPercentage(runPlanData.getWeekIndex(), lessonIndex));
                    return rPlan;
                }

            }else{
                Plan rPlan = new Plan();
                rPlan.setRun_plan_id(runPlanId);
                rPlan.setLesson_index(lessonIndex + 1);
                rPlan.setPlan_percentage(calPlanPercentage(runPlanData.getWeekIndex(), lessonIndex));
                return rPlan;
            }
        }
    }

    /**
     * 完成计划
     */
    /*
    public void finishPlanForLocal(){
        Map<String, Object> map = runPlanSharedPreferences.getPlanSchedule();
        int runPlanId = (int)map.get("runPlanId");
        int lessonIndex = (int)map.get("lessonIndex");
        float planPercentage = (float)map.get("planPercentage");

        //该跑步计划为提示文本时,存储下一个跑步计划id
        if(getPlanState() == 3){
            runPlanSharedPreferences.setPlanSchedule(runPlanId+1,1,planPercentage);
        }else{
            //跑步计划执行到该周第三课时,存储下一个跑步计划id;否则,存储下一个跑步课程
            if(lessonIndex==3){
                //该跑步计划包含选项,跑步计划id+2
                if(getPlanState() == 2){
                    runPlanSharedPreferences.setPlanSchedule(runPlanId+2,1,calPlanPercentage(runPlanData.getWeekIndex(),lessonIndex));
                }else{
                    runPlanSharedPreferences.setPlanSchedule(runPlanId+1,1,calPlanPercentage(runPlanData.getWeekIndex(),lessonIndex));
                }

            }else{
                runPlanSharedPreferences.setPlanSchedule(runPlanId,lessonIndex+1,calPlanPercentage(runPlanData.getWeekIndex(),lessonIndex));
            }
        }

    }*/



    /**
     * 计算计划进度百分比
     * @param weekIndex 第几周
     * @param lessonIndex 第几课
     * @return 进度百分比
     */
    private double calPlanPercentage(int weekIndex,int lessonIndex){
        return ((double)((runPlanData.getWeekIndex()-1)*3+lessonIndex))/(13*3);
    }


}
