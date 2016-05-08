package com.starun.www.starun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.starun.www.starun.R;
import com.starun.www.starun.model.data.RunPlanData;

import java.util.ArrayList;

/**
 * Created by TPIAN on 16/5/5.
 */
public class PlanListAdapter extends BaseAdapter {
    public static final String TAG = "RecordListAdapter";
    Context context;
    private static LayoutInflater inflater=null;
    ArrayList<RunPlanData> planDatas;

    public PlanListAdapter(Context context, ArrayList<RunPlanData> planDatas) {
        this.context = context;
        this.planDatas = planDatas;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return planDatas.size();
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
        TextView phase_tv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        View rootView = inflater.inflate(R.layout.plan_listview_item, null);


//        View rootView = inflater.inflate(R.layout.plan_listview_item, parent,false);
//        holder.phase_tv = (TextView)rootView.findViewById(R.id.phase_tv);
//        holder.phase_tv.setText(planDatas.get(position).getWeekIndex()+"");
//        return rootView;
        return rootView;
    }


}
