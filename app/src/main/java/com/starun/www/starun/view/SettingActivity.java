package com.starun.www.starun.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.starun.www.starun.R;

public class SettingActivity extends AppCompatActivity {
    private ToggleButton mTogBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_setting);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        mTogBtn = (ToggleButton) findViewById(R.id.mTogBtn_notice);
        mTogBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    //选中
                } else {
                    //未选中
                }
            }
        });// 添加监听事件
    }
}
