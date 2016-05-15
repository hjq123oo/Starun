package com.starun.www.starun.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.starun.www.starun.R;
import com.starun.www.starun.model.data.WarmUpData;
import com.starun.www.starun.presenter.WarmUpPresenter;
import com.starun.www.starun.presenter.impl.WarmUpPresenterImpl;
import com.starun.www.starun.pview.WarmUpView;

public class WarmupActivity extends AppCompatActivity implements WarmUpView{
    WarmUpPresenter warmUpPresenter;
    TextView tv_CountDown,tv_Title,tvIgnoreWarmup;
    ImageView iv_Pic;
    Button btn_Continue,btn_Pause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warmup);
        init();
        warmUpPresenter.doWarmUpStart();
    }

    public void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_warmup);
        setSupportActionBar(toolbar);
        tvIgnoreWarmup = (TextView) findViewById(R.id.tv_ignore_warmup);
        tv_CountDown = (TextView) findViewById(R.id.tv_warmup_count_down);
        tv_Title = (TextView) findViewById(R.id.tv_warmup_title);
        iv_Pic = (ImageView) findViewById(R.id.iv_warmup_pic);
        btn_Continue = (Button) findViewById(R.id.btn_warmup_continue);
        btn_Pause = (Button) findViewById(R.id.btn_warmup_pause);
        warmUpPresenter = new WarmUpPresenterImpl(WarmupActivity.this);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                   case  R.id.btn_warmup_continue:
                        warmUpPresenter.doWarmUpStart();
                       break;
                    case R.id.btn_warmup_pause:
                        warmUpPresenter.doWarmUpPause();
                        break;
                    case R.id.tv_ignore_warmup:
                        warmUpPresenter.doWarmUpStop();
                        break;
                    default:
                        break;
                }
            }
        };
        btn_Pause.setOnClickListener(listener);
        btn_Continue.setOnClickListener(listener);
        tvIgnoreWarmup.setOnClickListener(listener);
    }

    @Override
    public Activity getActivity() {
        return WarmupActivity.this;
    }

    @Override
    public void onUpdateWarmUpInfo(int progress, WarmUpData warmUpData) {
        tv_Title.setText(warmUpData.getTitle());
        btn_Continue.setVisibility(View.INVISIBLE);
        btn_Pause.setVisibility(View.VISIBLE);
        //iv_Pic.setImageResource(warmUpData.getImgId());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        options.inPurgeable = true;
        options.inScaled = true;
        Resources res=getResources();
        Bitmap bitmap=BitmapFactory.decodeResource(res, warmUpData.getImgId(),options);
        iv_Pic.setImageBitmap(bitmap);

        //iv_Pic.setImageDrawable(getResources().getDrawable(warmUpData.getImgId()));

    }

    @Override
    public void onUpdateWarmUpProgress(int progress) {
        tv_CountDown.setText(progress+"");
        btn_Continue.setVisibility(View.INVISIBLE);
        btn_Pause.setVisibility(View.VISIBLE);
    }

    @Override
    public void onWarmUpStart() {
        btn_Continue.setVisibility(View.INVISIBLE);
        btn_Pause.setVisibility(View.VISIBLE);
    }

    @Override
    public void onWarmUpPause() {
        btn_Continue.setVisibility(View.VISIBLE);
        btn_Pause.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onWarmUpStop() {
        Intent i = new Intent(WarmupActivity.this,ExerciseActivity.class);
        startActivity(i);
        finish();
    }
}
