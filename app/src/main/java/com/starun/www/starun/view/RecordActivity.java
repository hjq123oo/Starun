package com.starun.www.starun.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.starun.www.starun.R;
import com.starun.www.starun.adapter.RecordListAdapter;
import com.starun.www.starun.dao.RunRecordDao;
import com.starun.www.starun.model.data.RunRecord;
import com.starun.www.starun.presenter.RunRecordPresenter;
import com.starun.www.starun.pview.RunRecordView;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity implements RunRecordView {
    public static final String TAG = "RecordActivity";
    ListView lv;
    RunRecordPresenter runRecordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        lv = (ListView) findViewById(R.id.record_lv);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("历史记录");
        


//        ArrayList<RunRecord> runRecords = new ArrayList<>();
//        RunRecord record1 = new RunRecord();
//        record1.setAvgSpeed(12);
//        record1.setEndTime(System.currentTimeMillis());
//        record1.setKilometer(12);
//        record1.setRunRecordId(111);
//        record1.setStartTime(System.currentTimeMillis() - 3600000);
//        record1.setUsrId(2323);
//        record1.setTraceEntity("Hello");
//        runRecords.add(record1);
//        runRecords.add(record1);
//        runRecords.add(record1);

        RunRecordDao runRecordDao = new RunRecordDao(getApplicationContext());
        List<RunRecord> runRecords = runRecordDao.getRunRecords();
        if (runRecords.size()>0) {
            lv.setAdapter(new RecordListAdapter(getApplicationContext(), (ArrayList) runRecords));
        }
        else {
            Log.d(TAG, "runRecords is null");
        }

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onShowRunRecords(List<RunRecord> records) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.record_menu_item:
                Log.d(TAG,"click record_menu_item");
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
