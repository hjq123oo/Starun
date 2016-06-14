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
public class PlanFragment  extends BaseFragment implements FriendOrRankListView{
    //标志位，标志已经初始化完成
    private boolean isPrepared;
    //是否已被加载过一次，第二次就不再去请求数据了
    private boolean mHasLoadedOnce;

    private UserAdapter userAdapter = null;
    private ListView listView = null;
    private View view = null;
    private FriendOrRankListPresenter friendOrRankListPresenter = null;
    private String user_id = null;

    public static final PlanFragment newInstance(Boolean rank){
        PlanFragment planFragment = new PlanFragment();
        Bundle bd = new Bundle();
        bd.putBoolean("rank",rank);
        planFragment.setArguments(bd);
        return planFragment;
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
                    friendOrRankListPresenter.showListForPlan(user_id);
                    //refreshData();
                    mHasLoadedOnce = true;
                } else {
                    // 加载失败
                    Log.i("PlanFragment","加载失败");
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
        if(0 == users.size()){
            Toast.makeText(this.getActivity(),"好友为空，请添加好友",Toast.LENGTH_LONG).show();
        }
        else {
            listView = (ListView) view.findViewById(R.id.user_list_view);
            userAdapter = new UserAdapter(this.getActivity().getApplicationContext(), users, friendOrRankListPresenter);
            listView.setAdapter(userAdapter);
        }
    }

    @Override
    public void showError() {
        Toast.makeText(this.getActivity().getApplicationContext(),"访问网络失败！",Toast.LENGTH_SHORT).show();
    }
}
