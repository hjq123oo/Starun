package com.starun.www.starun.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.starun.www.starun.R;
import com.starun.www.starun.view.utilview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView tvTotalDist,tvTotalTime,tvStage;
    TextView tvNickName;
    CircleImageView ivIcon;
    //Button btnMainPage,btnFriends,btnRank,btnSetting,btnExit;
    Button planExercise;
    TextView dailyExercise;

    TextView tvRunPlan;
    TextView tvRunRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.mainactivity_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tvTotalDist = (TextView) findViewById(R.id.tv_total_dist);
        tvTotalTime = (TextView) findViewById(R.id.tv_total_time);
        tvStage = (TextView) findViewById(R.id.tv_total_stage);
        tvNickName = (TextView) findViewById(R.id.tv_navi_nickname);
        ivIcon = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.civ_icon);

        dailyExercise = (TextView) findViewById(R.id.tv_daily_exercise);
        planExercise = (Button) findViewById(R.id.plan_btn);

        tvRunPlan =  (TextView) findViewById(R.id.tv_run_plan);
        tvRunRecord =  (TextView) findViewById(R.id.tv_run_record);

        tvRunPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PlanActivity.class);
                startActivity(intent);
            }
        });

        tvRunRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RecordActivity.class);
                startActivity(intent);
            }
        });

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = null;
                switch (v.getId()){

                    case R.id.tv_daily_exercise:
                        i = new Intent(MainActivity.this,WarmupActivity.class);
                        i.putExtra("StartActivity","ExerciseActivity");
                        startActivity(i);
                        //finish();
                        break;
                    case  R.id.plan_btn:
                        i = new Intent(MainActivity.this,WarmupActivity.class);
                        i.putExtra("StartActivity","RunPlanActivity");
                        startActivity(i);
                        //finish();
                        break;

                    default:
                        break;
                }
            }
        };
        dailyExercise.setOnClickListener(listener);
        planExercise.setOnClickListener(listener);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();

        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main_page) {
            // Handle the camera action
        } else if (id == R.id.nav_friends) {

        } else if (id == R.id.nav_rank) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_exit) {

        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }
}
