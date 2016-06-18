package com.starun.www.starun.view.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.FriendOrRankListPresenter;
import com.starun.www.starun.presenter.impl.FriendOrRankListPresenterImpl;
import com.starun.www.starun.pview.FriendOrRankListView;
import com.starun.www.starun.server.data.RunTotalInfo;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.view.UserInfoActivity;
import com.starun.www.starun.view.application.MyApplication;
import com.starun.www.starun.view.customview.UserAdapter;
import com.starun.www.starun.view.utilview.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yearsj on 2016/5/20.
 */
public class RankDailyFragment extends BaseFragment implements FriendOrRankListView{
    //标志位，标志已经初始化完成
    private boolean isPrepared;
    //是否已被加载过一次，第二次就不再去请求数据了
    private boolean mHasLoadedOnce;

    private UserAdapter userAdapter = null;
    private ListView listView = null;
    private View view = null;
    private FriendOrRankListPresenter friendOrRankListPresenter = null;
    private String user_id = null;
    private TextView no1 = null;
    private TextView no2 = null;
    private TextView no3 = null;
    private CircleImageView zhuangyuan = null;
    private CircleImageView bangyan = null;
    private CircleImageView tanhua = null;
    private View no1_3 = null;

    public static final RankDailyFragment newInstance(Boolean rank){
        RankDailyFragment rankDailyFragment = new RankDailyFragment();
        Bundle bd = new Bundle();
        bd.putBoolean("rank",rank);
        rankDailyFragment.setArguments(bd);
        return rankDailyFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan_rank,container, false);
        friendOrRankListPresenter = new FriendOrRankListPresenterImpl(this,getArguments().getBoolean("rank"));
        MyApplication myApplication = (MyApplication) this.getActivity().getApplicationContext();
        if(null!=myApplication.getUser()){
            user_id = String.valueOf(myApplication.getUser().getUser_id());
        }
        no1_3 = (View)view.findViewById(R.id.no1_3);
        no1_3.setVisibility(View.GONE);
        no1 = (TextView)view.findViewById(R.id.zhuangyuan_name);
        no2 = (TextView)view.findViewById(R.id.bangyan_name);
        no3 = (TextView)view.findViewById(R.id.tanhua_name);
        zhuangyuan = (CircleImageView)view.findViewById(R.id.zhuangyuan);
        bangyan = (CircleImageView)view.findViewById(R.id.bangyan);
        tanhua = (CircleImageView)view.findViewById(R.id.tanhua);

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
                    friendOrRankListPresenter.showListForDailyRank();
                    //refreshData();
                    mHasLoadedOnce = true;
                } else {
                    // 加载失败
                    Log.i("RankDailyFragment", "加载失败");
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
        if(0!=users.size()) {
            no1_3.setVisibility(View.VISIBLE);
            for (int i = 0; i < 3&&i<users.size(); i++) {
                switch (i) {
                    case 0: no1.setText(users.get(i).getUsername());
                        zhuangyuan.setOnClickListener(new ClickListener(users.get(i)));
                        zhuangyuan.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(users.get(i).getHeadImgPath(), "drawable", this.getActivity().getPackageName())));;break;
                    case 1: no2.setText(users.get(i).getUsername()); bangyan.setOnClickListener(new ClickListener(users.get(i)));
                        bangyan.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(users.get(i).getHeadImgPath(), "drawable", this.getActivity().getPackageName())));;break;
                    case 2: no3.setText(users.get(i).getUsername()); tanhua.setOnClickListener(new ClickListener(users.get(i)));
                        tanhua.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(users.get(i).getHeadImgPath(), "drawable", this.getActivity().getPackageName())));;break;
                }
            }
            if (users.size() > 3) {
                listView = (ListView) view.findViewById(R.id.user_list_view_rank);
                userAdapter = new UserAdapter(this.getActivity().getApplicationContext(), users.subList(3, users.size()), friendOrRankListPresenter, 4);
                listView.setAdapter(userAdapter);
            }
        }
        else{
            Toast.makeText(this.getContext(),"当前无数据",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showError() {
        Toast.makeText(this.getActivity().getApplicationContext(),"访问网络失败！",Toast.LENGTH_SHORT).show();
    }

    /**
     * 内部类---点击事件
     */
    public class ClickListener implements View.OnClickListener{
        private User user;
        public ClickListener(User user){
            this.user = user;
        }
        @Override
        public void onClick(View v){
            //此处跳转到详情页面
            Intent intent = new Intent();
            intent.setClass(getActivity(), UserInfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            intent.putExtras(bundle);
            getActivity().startActivity(intent);
        }
    }
}
