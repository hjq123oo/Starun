package com.starun.www.starun.presenter;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by hjq on 2016/5/5.
 */
public interface RunPlanPresenter {

    @IntDef({RUN_PLAN, CHECK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RUN_TAG_TYPE {}
    public static final int RUN_PLAN = 0;
    public static final int CHECK = 1;

    void doLoadRunPlan(int week);
    void doLoadTag(@RUN_TAG_TYPE int type);
}
