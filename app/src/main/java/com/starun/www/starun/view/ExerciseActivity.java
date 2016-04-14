package com.starun.www.starun.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.starun.www.starun.R;
import com.starun.www.starun.model.IRun;
import com.starun.www.starun.presenter.RunPresenter;
import com.starun.www.starun.presenter.impl.RunPresenterImpl;
import com.starun.www.starun.pview.RunView;


public class ExerciseActivity extends Activity implements RunView{

    private RunPresenter runPresenter = null;
    private BottomBar mBottomBar;
    //private MsgReceiver msgReceiver = null;   //广播
    private TextView dis_tv = null;
    private Button start_btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemOne) {
                    // The user selected item number one.
                }
                if (menuItemId == R.id.bottomBarItemTwo) {
                    //two
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemOne) {
                    // The user reselected item number one, scroll your content to top.
                }
                if (menuItemId == R.id.bottomBarItemTwo) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });

        //初始化
        init();
    }

    //初始化控件
    private void init(){
        dis_tv = (TextView)findViewById(R.id.dis_tv);
        start_btn = (Button)findViewById(R.id.start_btn);
        runPresenter = new RunPresenterImpl(this);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 在此之前启动计时器
                 */
                runPresenter.doRunStart();//开始跑步
            }
        });

        Button map = (Button)findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });
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
    public void onUpdateRunInfo(IRun run) {
        /**
         *   写界面的人在此补充方法
         *   用的数据刷新界面
         */

        Toast.makeText(this,"distance="+run.getKilometer(),Toast.LENGTH_LONG).show();
    }

    /**
     * 注：
     * 计时器参考界面
     * 开始跑步时，先启动计时器，再runStart()
     * 暂停跑步，先暂停计时器，再runPause()
     * 停止跑步，先停止计时器，再runStop()，最后调用saveInfo()
     */
}
