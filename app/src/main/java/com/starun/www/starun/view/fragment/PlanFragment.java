package com.starun.www.starun.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.starun.www.starun.R;

/**
 * Created by yearsj on 2016/5/20.
 */
public class PlanFragment  extends BaseFragment {
    //标志位，标志已经初始化完成
    private boolean isPrepared;
    //是否已被加载过一次，第二次就不再去请求数据了
    private boolean mHasLoadedOnce;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.userlist,container, false);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    //刷新界面
    private void refreshData(){
        Toast.makeText(this.getContext(), "PlanFragment", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }

        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //显示加载进度对话框
                System.out.println("正在加载...");
            }

            @Override
            protected void onPostExecute(Boolean isSuccess) {
                if (isSuccess) {
                    // 加载成功
                    refreshData();
                    mHasLoadedOnce = true;
                } else {
                    // 加载失败
                }
                //关闭对话框
               // UIHelper.hideDialogForLoading();
            }

            @Override
            protected Boolean doInBackground(Void... params) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return true;
            }
        }.execute();
    }
}
