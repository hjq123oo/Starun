package com.starun.www.starun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.starun.www.starun.R;
import com.starun.www.starun.model.data.RunRecord;

import java.util.ArrayList;

/**
 * Created by TPIAN on 16/5/5.
 */
public class RecordListAdapter extends BaseAdapter {

    Context context;
    private static LayoutInflater inflater=null;
    ArrayList<RunRecord> records;

    public RecordListAdapter(Context context,ArrayList<RunRecord> records) {
        this.context = context;
        this.records = records;
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = inflater.inflate(R.layout.record_listview_item, null);


        return rootView;
    }
}
