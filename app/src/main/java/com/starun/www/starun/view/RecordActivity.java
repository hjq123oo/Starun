package com.starun.www.starun.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.RunRecordPresenter;
import com.starun.www.starun.presenter.impl.RunRecordPresenterImpl;
import com.starun.www.starun.pview.RunRecordView;
import com.starun.www.starun.server.data.RunRecord;
import com.starun.www.starun.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity implements RunRecordView {

    ListView lv;
    SwipeRefreshLayout swipeRefreshLayout;
    RunRecordPresenter runRecordPresenter;

    RecordListAdapter recordListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        lv = (ListView) findViewById(R.id.record_lv);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_layout);
        runRecordPresenter = new RunRecordPresenterImpl(this);


        swipeRefreshLayout.setProgressViewEndTarget(true, 100);

        runRecordPresenter.doRunRecordsInit();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        runRecordPresenter.doRunRecordsLoad();
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        /*
        RunRecordDao runRecordDao = new RunRecordDao(getApplicationContext());
        List<RunRecord> runRecords = runRecordDao.getRunRecords();
        if (runRecords.size()>0) {
            lv.setAdapter(new RecordListAdapter(getApplicationContext(), (ArrayList) runRecords));
        }
        else {
            Log.d(TAG, "runRecords is null");
        }*/

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onShowRunRecords(List<RunRecord> runRecords) {
        recordListAdapter = new RecordListAdapter(this, runRecords);
        lv.setAdapter(recordListAdapter);
    }

    @Override
    public void onUpdateRunRecords() {
        recordListAdapter.notifyDataSetChanged();
    }


    class RecordListAdapter extends BaseAdapter {
        Context context;
        private LayoutInflater inflater=null;
        List<RunRecord> records;

        public RecordListAdapter(Context context,List<RunRecord> records) {
            this.context = context;
            this.records = records;
            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return records.size();
        }

        @Override
        public Object getItem(int position) {
            return records.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class Holder
        {
            ImageView icon1;
            ImageView icon2;
            TextView date_tv;
            TextView distance_tv;
            TextView spend_time_tv;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = new Holder();;
            View rootView = inflater.inflate(R.layout.record_listview_item, parent,false);
            holder.date_tv = (TextView)rootView.findViewById(R.id.date_tv);
            holder.distance_tv = (TextView)rootView.findViewById(R.id.distance_tv);
            holder.spend_time_tv = (TextView)rootView.findViewById(R.id.spend_time_tv);
            holder.icon1 = (ImageView)rootView.findViewById(R.id.icon1);
            holder.icon2 = (ImageView)rootView.findViewById(R.id.icon2);
            holder.date_tv.setText(TimeUtils.long2DateStr(records.get(position).getStartTime()));
            holder.distance_tv.setText(records.get(position).getKilometer()+"公里");
            holder.spend_time_tv.setText(TimeUtils.long2MS(records.get(position).getRunTime()));
            return rootView;
        }
    }


}
