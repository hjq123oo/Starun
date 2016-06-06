package com.starun.www.starun.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.starun.www.starun.model.data.Setting;

/**
 * Created by hjq on 2016/6/6.
 */
public class SettingSharedPreferences {
    private Context context;

    public SettingSharedPreferences(Context context){
        this.context = context;

    }

    public void setSetting(Setting setting){
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("hasNotice", setting.isHasNotice());
        editor.putBoolean("hasVoice", setting.isHasVoice());
        editor.commit();
    }

    public Setting getSetting(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
        boolean hasNotice = sharedPreferences.getBoolean("hasNotice", true);
        boolean hasVoice = sharedPreferences.getBoolean("hasVoice", true);

        Setting setting = new Setting();

        setting.setHasNotice(hasNotice);
        setting.setHasVoice(hasVoice);

        return setting;
    }
}
