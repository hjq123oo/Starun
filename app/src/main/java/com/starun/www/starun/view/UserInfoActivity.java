package com.starun.www.starun.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.UserInfoPresenter;
import com.starun.www.starun.presenter.impl.UserInfoPresenterImpl;
import com.starun.www.starun.pview.UserInfoView;
import com.starun.www.starun.server.data.RunTotalInfo;
import com.starun.www.starun.view.application.MyApplication;
import com.starun.www.starun.view.utilview.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInfoActivity extends AppCompatActivity implements UserInfoView{
    private Button btnTool;
    private LinearLayout ownLinearLayout;
    private ImageButton btnBack;
    private View historyView;
    private View planView;

    private CircleImageView circleImageView;
    private TextView nicknameTextView;
    private TextView distanceTextView;
    private TextView timeTextView;
    private ProgressBar planProgressBar;

    private UserInfoPresenter userInfoPresenter;


    private int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        init();

    }


    private void init(){
        btnTool = (Button)findViewById(R.id.btn_tool);
        ownLinearLayout = (LinearLayout)findViewById(R.id.linearLayout_own);
        btnBack = (ImageButton)findViewById(R.id.btn_back);

        historyView = (View)findViewById(R.id.view_history);
        planView = (View)findViewById(R.id.view_plan);

        circleImageView = (CircleImageView)findViewById(R.id.iv_avatar);
        nicknameTextView = (TextView)findViewById(R.id.tv_nickname);

        distanceTextView = (TextView)findViewById(R.id.tv_distance);
        timeTextView = (TextView)findViewById(R.id.tv_time);
        planProgressBar = (ProgressBar)findViewById(R.id.progress_plan);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        user_id = bundle.getInt("user_id");

        userInfoPresenter = new UserInfoPresenterImpl(this);

        userInfoPresenter.doLoadUserInfo(user_id);


    }


    @Override
    public void onShowOwn(RunTotalInfo runTotalInfo) {
        btnTool.setText("头像");
        ownLinearLayout.setVisibility(View.VISIBLE);


       showRunTotalInfo(runTotalInfo);


        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this,AvatarActivity.class);
                startActivity(intent);
            }
        });

        btnTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this,AvatarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        historyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this,RecordActivity.class);
                startActivity(intent);
            }
        });

        planView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this,PlanActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onShowFriend(RunTotalInfo runTotalInfo) {

        btnTool.setText("删除好友");
        ownLinearLayout.setVisibility(View.GONE);

        showRunTotalInfo(runTotalInfo);

        btnTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfoPresenter.doFriendDelete();
            }
        });
    }

    @Override
    public void onShowNonFriend(RunTotalInfo runTotalInfo) {
        btnTool.setText("添加好友");
        ownLinearLayout.setVisibility(View.GONE);

        showRunTotalInfo(runTotalInfo);

        btnTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfoPresenter.doFriendAdd();
            }
        });
    }


    private void showRunTotalInfo(RunTotalInfo runTotalInfo){
        nicknameTextView.setText(runTotalInfo.getNickname());

        circleImageView.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(runTotalInfo.getHeadImgPath(), "drawable", getPackageName())));

        distanceTextView.setText(String.format("%.2f",runTotalInfo.getTotal_distance())+"km");

        long totalTime = runTotalInfo.getTotal_time();
        double hours = ((double)totalTime)/1000/60/60;

        timeTextView.setText(String.format("%.2f", hours) + "h");

        planProgressBar.setProgress((int)(runTotalInfo.getPlan_percentage()*100));
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
