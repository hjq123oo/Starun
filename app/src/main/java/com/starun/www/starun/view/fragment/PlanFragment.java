package com.starun.www.starun.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.starun.www.starun.R;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.view.customview.UserAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yearsj on 2016/5/20.
 */
public class PlanFragment  extends BaseFragment {
    //标志位，标志已经初始化完成
    private boolean isPrepared;
    //是否已被加载过一次，第二次就不再去请求数据了
    private boolean mHasLoadedOnce;

    private UserAdapter userAdapter = null;
    private ListView listView = null;
    private List<User> userList = null;

    private View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan,container, false);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    private void initView(){
        listView = (ListView)view.findViewById(R.id.user_list_view);
        userList = new ArrayList<User>();

        User user1 = new User();
        user1.setUser_id(1);
        user1.setUsername("yearsj");
        User user2 = new User();
        user2.setUser_id(2);
        user2.setUsername("rr");
        User user3 = new User();
        user3.setUser_id(3);
        user3.setUsername("xx");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        userAdapter = new UserAdapter(this.getActivity().getApplicationContext(),userList);
        listView.setAdapter(userAdapter);
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
                    initView();
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
