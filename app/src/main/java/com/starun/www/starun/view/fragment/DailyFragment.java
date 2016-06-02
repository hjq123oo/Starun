package com.starun.www.starun.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.FriendOrRankListPresenter;
import com.starun.www.starun.presenter.impl.FriendOrRankListPresenterImpl;
import com.starun.www.starun.pview.FriendOrRankListView;
import com.starun.www.starun.server.data.RunTotalInfo;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.view.application.MyApplication;
import com.starun.www.starun.view.customview.UserAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yearsj on 2016/5/20.
 */
public class DailyFragment extends BaseFragment implements FriendOrRankListView{
    //标志位，标志已经初始化完成
    private boolean isPrepared;
    //是否已被加载过一次，第二次就不再去请求数据了
    private boolean mHasLoadedOnce;

    private UserAdapter userAdapter = null;
    private ListView listView = null;
    private View view = null;
    private FriendOrRankListPresenter friendOrRankListPresenter = null;
    private String user_id = null;

    public static final DailyFragment newInstance(Boolean rank){
        DailyFragment dailyFragment = new DailyFragment();
        Bundle bd = new Bundle();
        bd.putBoolean("rank",rank);
        dailyFragment.setArguments(bd);
        return dailyFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan,container, false);
        friendOrRankListPresenter = new FriendOrRankListPresenterImpl(this,getArguments().getBoolean("rank"));
        MyApplication myApplication = (MyApplication) this.getActivity().getApplicationContext();
        if(null!=myApplication.getUser()){
            user_id = String.valueOf(myApplication.getUser().getUser_id());
        }
        isPrepared = true;
        lazyLoad();
        return view;
    }

    //刷新界面
    private void refreshData(){
        Toast.makeText(this.getContext(), "DailyFragment", Toast.LENGTH_LONG).show();
        List<User> users = new ArrayList<User>();
        listView = (ListView)view.findViewById(R.id.user_list_view);
        users = new ArrayList<User>();
        User user = new User();
        user.setUsername("yearsj");
        user.setNickname("yearsj");
        user.setUser_id(1);
        users.add(user);

        User user2 = new User();
        user2.setUsername("hjq");
        user2.setNickname("hjq");
        user2.setUser_id(2);
        users.add(user2);

        User user3 = new User();
        user3.setUsername("lxn");
        user3.setNickname("lxn");
        user3.setUser_id(1);
        users.add(user3);

        User user4 = new User();
        user4.setUsername("tala");
        user4.setNickname("tala");
        user4.setUser_id(1);
        users.add(user4);

        userAdapter = new UserAdapter(this.getActivity().getApplicationContext(),users,friendOrRankListPresenter);
        listView.setAdapter(userAdapter);
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
                    //friendOrRankListPresenter.showListForDaily(user_id);
                    refreshData();
                    mHasLoadedOnce = true;
                } else {
                    // 加载失败
                    Log.i("DailyFragment", "加载失败");
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

    @Override
    public void showUserList(List<User> users) {
        listView = (ListView)view.findViewById(R.id.user_list_view);
        userAdapter = new UserAdapter(this.getActivity().getApplicationContext(),users,friendOrRankListPresenter);
        listView.setAdapter(userAdapter);
    }

    @Override
    public void showFriendDetail(RunTotalInfo runTotalInfo, boolean isfriend) {
        if(null!=runTotalInfo){
            //跳转到详情页面
            Toast.makeText(this.getActivity().getApplicationContext(),"在此跳转到详情页面",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showError() {
        Toast.makeText(this.getActivity().getApplicationContext(),"访问网络失败！",Toast.LENGTH_SHORT).show();
    }
}
