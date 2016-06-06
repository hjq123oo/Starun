package com.starun.www.starun.view.application;

import android.app.Application;

import com.starun.www.starun.dao.SettingSharedPreferences;
import com.starun.www.starun.model.data.Setting;
import com.starun.www.starun.server.data.User;

/**
 * Created by yearsj on 2016/5/22.
 */
public class MyApplication extends Application{

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user = null;

    private Setting setting;

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SettingSharedPreferences settingSharedPreferences = new SettingSharedPreferences(this);
        setting = settingSharedPreferences.getSetting();
    }


}
