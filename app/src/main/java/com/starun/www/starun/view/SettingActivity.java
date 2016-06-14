package com.starun.www.starun.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.starun.www.starun.R;
import com.starun.www.starun.dao.SettingSharedPreferences;
import com.starun.www.starun.model.data.Setting;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.view.application.MyApplication;
import com.starun.www.starun.view.utilview.CircleImageView;


public class SettingActivity extends AppCompatActivity {
    private ImageButton btnBack;

    private ToggleButton mTogBtnNotice;
    private ToggleButton mTogBtnVoice;


    private CircleImageView civSettingIcon;
    private TextView tvSettingNickname;

    private User user;
    private Setting setting;

    private SettingSharedPreferences settingSharedPreferences;


    private View musicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnBack = (ImageButton)findViewById(R.id.btn_back);

        mTogBtnNotice = (ToggleButton) findViewById(R.id.mTogBtn_notice);
        mTogBtnVoice = (ToggleButton) findViewById(R.id.mTogBtn_voice);

        civSettingIcon = (CircleImageView) findViewById(R.id.civ_setting_icon);
        tvSettingNickname = (TextView) findViewById(R.id.tv_setting_nickname);

        musicView = findViewById(R.id.view_music);

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

        musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
