package com.starun.www.starun.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.starun.www.starun.R;
import com.starun.www.starun.model.IRunPlanExecution;
import com.starun.www.starun.presenter.RunPlanExecutionPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RunPlanMainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RunPlanMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RunPlanMainFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Activity mActivity;
    private AppCompatActivity mAppCompatActivity;
    private TextView dis_tv = null,time_tv,plan_time_text_tv;
    private Button start_btn = null,stop_btn = null,pause_btn = null;
    private Chronometer timer;
    private View view;
    RunPlanExecutionPresenter runPlanExecutionPresenter = null;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RunPlanMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RunPlanMainFragment newInstance(String param1, String param2) {
        RunPlanMainFragment fragment = new RunPlanMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RunPlanMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // mActivity = getActivity();

        view = inflater.inflate(R.layout.fragment_run_plan_main, container, false);
        runPlanExecutionPresenter = ((RunPlanActivity)getActivity()).getRunPlanExecutionPresenter();
        init();
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
    public void init(){
        dis_tv = (TextView)view.findViewById(R.id.plan_run_tv);
        time_tv = (TextView) view.findViewById(R.id.plan_time_tv);
        plan_time_text_tv = (TextView)view.findViewById(R.id.plan_time_text_tv);
        start_btn = (Button)view.findViewById(R.id.plan_run_start_btn);
        pause_btn = (Button) view.findViewById(R.id.plan_run_pause_btn);
        stop_btn = (Button) view.findViewById(R.id.plan_run_stop_btn);
        timer = (Chronometer) view.findViewById(R.id.plan_timer);
        start_btn.setVisibility(View.INVISIBLE);
        stop_btn.setVisibility(View.INVISIBLE);
        pause_btn.setVisibility(View.VISIBLE);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.plan_run_start_btn:
                        runPlanExecutionPresenter.doRunStart();
                        break;
                    case R.id.plan_run_pause_btn:
                        runPlanExecutionPresenter.doRunPause();
                        break;
                    case R.id.plan_run_stop_btn://跳到地图界面
                        new AlertDialog.Builder(getActivity())
                                .setMessage("是否结束本次运动")//设置显示的内容
                                .setPositiveButton("结束", new DialogInterface.OnClickListener() {//添加确定按钮
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                        runPlanExecutionPresenter.doRunStop();

                                    }
                                }).setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加返回按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//响应事件
                            }
                        }).show();//在按键响应事件中显示此对话框
                        break;

                    default:
                        break;
                }

            }
        };
        start_btn.setOnClickListener(listener);
        stop_btn.setOnClickListener(listener);
        pause_btn.setOnClickListener(listener);
    }
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

    public Chronometer getChronometer(){
        return timer;
    }


    public void onRunStart() {
        timer.setBase(convertStrTimeToLong(timer.getText().toString()));
        timer.start();
        start_btn.setVisibility(View.INVISIBLE);
        stop_btn.setVisibility(View.INVISIBLE);
        pause_btn.setVisibility(View.VISIBLE);
    }

    public void onRunPause() {
        timer.stop();
        start_btn.setVisibility(View.VISIBLE);
        stop_btn.setVisibility(View.VISIBLE);
        pause_btn.setVisibility(View.INVISIBLE);
    }

    public void onRunStop() {
        Intent intent = new Intent(getActivity().getApplicationContext(), MapActivity.class);
        startActivity(intent);
        timer.stop();
    }

    public void onUpdateInfo(IRunPlanExecution iRunPlanExecution) {
        dis_tv.setText(iRunPlanExecution.getKilometer()+"");
        plan_time_text_tv.setText(iRunPlanExecution.getMovementState());
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        String dateFormatted = dateFormat.format(new Date(iRunPlanExecution.getTime()));
        time_tv.setText(dateFormatted);

    }
}
