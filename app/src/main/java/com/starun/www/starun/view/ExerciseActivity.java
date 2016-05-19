package com.starun.www.starun.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.starun.www.starun.R;
import com.starun.www.starun.model.IRun;
import com.starun.www.starun.presenter.RunPresenter;
import com.starun.www.starun.presenter.impl.RunPresenterImpl;
import com.starun.www.starun.pview.RunView;


public class ExerciseActivity extends AppCompatActivity implements RunView,MusicFragment.OnFragmentInteractionListener{

    private RunPresenter runPresenter = null;
  //  private BottomBar mBottomBar;
    //private MsgReceiver msgReceiver = null;   //广播
    private TextView dis_tv = null;
    private Button start_btn = null,stop_btn = null,pause_btn = null;
    private Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        //初始化
        init();
        //开始跑步
        timer.start();
        runPresenter.doRunStart();//开始跑步
    }

    //初始化控件
    private void init(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_exercise);
       setSupportActionBar(toolbar);
        dis_tv = (TextView)findViewById(R.id.run_tv);
        start_btn = (Button)findViewById(R.id.run_start_btn);
        pause_btn = (Button) findViewById(R.id.run_pause_btn);
        stop_btn = (Button) findViewById(R.id.run_stop_btn);
        runPresenter = new RunPresenterImpl(this);
        timer = (Chronometer) findViewById(R.id.timer);
        start_btn.setVisibility(View.INVISIBLE);
        stop_btn.setVisibility(View.INVISIBLE);
        pause_btn.setVisibility(View.VISIBLE);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.run_start_btn:
                        timer.setBase(convertStrTimeToLong(timer.getText().toString()));
                        timer.start();
                        runPresenter.doRunStart();//开始跑步
                        start_btn.setVisibility(View.INVISIBLE);
                        stop_btn.setVisibility(View.INVISIBLE);
                        pause_btn.setVisibility(View.VISIBLE);
                        break;
                    case R.id.run_pause_btn:
                        timer.stop();
                        runPresenter.doRunPause();
                        start_btn.setVisibility(View.VISIBLE);
                        stop_btn.setVisibility(View.VISIBLE);
                        pause_btn.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.run_stop_btn://跳到地图界面
                        new AlertDialog.Builder(ExerciseActivity.this)
                                .setMessage("是否结束本次运动")//设置显示的内容
                                .setPositiveButton("结束", new DialogInterface.OnClickListener() {//添加确定按钮
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                        runPresenter.doRunStop();
                                        runPresenter.saveRunInfo();
                                        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                                        startActivity(intent);
                                    }
                                }).setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加返回按钮
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {//响应事件
                                    }
                        }).show();//在按键响应事件中显示此对话框
                        break;
                    case R.id.map_btn:
                        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

            }
        };
        start_btn.setOnClickListener(listener);
        stop_btn.setOnClickListener(listener);
        pause_btn.setOnClickListener(listener);
        ImageButton map = (ImageButton)findViewById(R.id.map_btn);
        map.setOnClickListener(listener);
        //动态注册广播接收器
        runPresenter.registerReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        runPresenter.unregisterReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //动态注册广播接收器
        runPresenter.registerReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //动态注销广播接收器
        runPresenter.unregisterReceiver();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Chronometer getChronometer() {
        return timer;
    }

    @Override
    public void onUpdateRunInfo(IRun run) {
        /**
         *   写界面的人在此补充方法
         *   用的数据刷新界面
         */

        dis_tv.setText((float) run.getKilometer() + "");
        //Toast.makeText(this,"distance="+run.getKilometer(),Toast.LENGTH_LONG).show();
    }

    /**
     * 注：
     * 计时器参考界面
     * 开始跑步时，先启动计时器，再runStart()
     * 暂停跑步，先暂停计时器，再runPause()
     * 停止跑步，先停止计时器，再runStop()，最后调用saveInfo()
     */

    protected long convertStrTimeToLong(String strTime) {
    // TODO Auto-generated method stub
         String []timeArry=strTime.split(":");
        long longTime=0;
        if (timeArry.length==2) {//如果时间是MM:SS格式
         longTime=Integer.parseInt(timeArry[0])*1000*60+Integer.parseInt(timeArry[1])*1000;
        }else if (timeArry.length==3){//如果时间是HH:MM:SS格式
         longTime=Integer.parseInt(timeArry[0])*1000*60*60+Integer.parseInt(timeArry[1]) *1000*60+Integer.parseInt(timeArry[0])*1000;
        }
        return SystemClock.elapsedRealtime()-longTime;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
