package com.starun.www.starun.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.starun.www.starun.R;
import com.starun.www.starun.model.data.RunRecord;
import com.starun.www.starun.utils.TimeUtils;

import java.util.ArrayList;

/**
 * Created by TPIAN on 16/5/5.
 */
public class RecordListAdapter extends BaseAdapter {
    public static final String TAG = "RecordListAdapter";
    Context context;
    private static LayoutInflater inflater=null;
    ArrayList<RunRecord> records;

    public RecordListAdapter(Context context,ArrayList<RunRecord> records) {
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
        return position;
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
        Holder holder = new Holder();
        Log.d(TAG, "inflater is "+inflater);
        View rootView = inflater.inflate(R.layout.record_listview_item, parent,false);
        holder.date_tv = (TextView)rootView.findViewById(R.id.date_tv);
        holder.distance_tv = (TextView)rootView.findViewById(R.id.distance_tv);
        holder.spend_time_tv = (TextView)rootView.findViewById(R.id.spend_time_tv);
        holder.icon1 = (ImageView)rootView.findViewById(R.id.icon1);
        holder.icon2 = (ImageView)rootView.findViewById(R.id.icon2);
        holder.date_tv.setText(TimeUtils.long2DateStr(records.get(position).getStartTime()));
        holder.distance_tv.setText(records.get(position).getKilometer()+"公里");
        holder.spend_time_tv.setText(TimeUtils.long2MS(records.get(position).getEndTime()-records.get(position).getStartTime()));
        return rootView;
    }
}
