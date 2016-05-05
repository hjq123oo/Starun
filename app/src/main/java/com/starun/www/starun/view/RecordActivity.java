package com.starun.www.starun.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.starun.www.starun.R;
import com.starun.www.starun.adapter.RecordListAdapter;
import com.starun.www.starun.model.data.RunRecord;
import com.starun.www.starun.presenter.RunRecordPresenter;
import com.starun.www.starun.presenter.impl.RunRecordPresenterImpl;
import com.starun.www.starun.pview.RunRecordView;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends Activity implements RunRecordView {

    ListView lv;
    RunRecordPresenter runRecordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        lv = (ListView) findViewById(R.id.listView);
        runRecordPresenter = new RunRecordPresenterImpl(this);
        runRecordPresenter.loadRunRecords();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onShowRunRecords(List<RunRecord> records) {
        lv.setAdapter(new RecordListAdapter(getApplicationContext(),(ArrayList)records));
    }
}
