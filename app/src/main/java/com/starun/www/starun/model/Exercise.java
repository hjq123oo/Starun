package com.starun.www.starun.model;

import java.lang.String;

/**
 * Created by hjq on 2016/3/28.
 */
public class Exercise {
    String title;//标题
    String progress;
    String desc1;
    String desc2;
    String displayView; //热身展示图片，跑步进度圈

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public String getDisplayView() {
        return displayView;
    }

    public void setDisplayView(String displayView) {
        this.displayView = displayView;
    }
}
