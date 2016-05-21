package com.starun.www.starun.presenter;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by hjq on 2016/5/5.
 */
public interface RunPlanPresenter {

    @IntDef({RUN_PLAN, CHECK,PRINCIPLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RUN_TAG_TYPE {}
    public static final int RUN_PLAN = 1;
    public static final int CHECK = 2;
    public static final int PRINCIPLE = -1;

    void doLoadRunPlan(int week); //这个是load第几周的
    void doLoadTag(@RUN_TAG_TYPE int type);

    //TODO: 这些是新加的接口
    // 加载最开始的原则
    void doLoadPrinciple();

    //加载中断检查的内容
    void doLoadCheck();
}
