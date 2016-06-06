package com.starun.www.starun.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.starun.www.starun.R;
import com.starun.www.starun.dao.SettingSharedPreferences;
import com.starun.www.starun.model.data.Setting;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.view.application.MyApplication;
import com.starun.www.starun.view.utilview.CircleImageView;


public class SettingActivity extends AppCompatActivity {
    private ToggleButton mTogBtnNotice;
    private ToggleButton mTogBtnVoice;

    private CircleImageView civSettingIcon;
    private TextView tvSettingNickname;

    private User user;
    private Setting setting;

    private SettingSharedPreferences settingSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_setting);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        mTogBtnNotice = (ToggleButton) findViewById(R.id.mTogBtn_notice);
        mTogBtnVoice = (ToggleButton) findViewById(R.id.mTogBtn_voice);

        civSettingIcon = (CircleImageView) findViewById(R.id.civ_setting_icon);
        tvSettingNickname = (TextView) findViewById(R.id.tv_setting_nickname);

        user = ((MyApplication)getApplication()).getUser();

        setting =  ((MyApplication)getApplication()).getSetting();

        tvSettingNickname.setText(user.getNickname());

        civSettingIcon.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(user.getHeadImgPath(), "drawable", getPackageName())));

        mTogBtnNotice.setChecked(setting.isHasNotice());

        mTogBtnVoice.setChecked(setting.isHasVoice());

        settingSharedPreferences = new SettingSharedPreferences(this);

        mTogBtnNotice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setHasNotice(isChecked);
                ((MyApplication)getApplication()).setSetting(setting);
                settingSharedPreferences.setSetting(setting);
            }
        });// 添加监听事件

        mTogBtnVoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setting.setHasVoice(isChecked);
                ((MyApplication)getApplication()).setSetting(setting);
                settingSharedPreferences.setSetting(setting);
            }
        });
    }
}
