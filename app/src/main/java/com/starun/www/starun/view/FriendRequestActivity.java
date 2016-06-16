package com.starun.www.starun.view;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.FriendRequestPresenter;
import com.starun.www.starun.presenter.impl.FriendRequestPresenterImpl;
import com.starun.www.starun.pview.FriendRequestView;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.server.data.UserNotification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FriendRequestActivity extends AppCompatActivity implements FriendRequestView{

    private ListView requestListView;
    private FriendRequestPresenter friendRequestPresenter;
    private FriendRequestListAdapter friendRequestListAdapter;

    private ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);

        btnBack = (ImageButton)findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        requestListView = (ListView) findViewById(R.id.lv_request);

        friendRequestPresenter= new FriendRequestPresenterImpl(this);

        friendRequestPresenter.doFriendRequestLoad();

    }


    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onFriendRequestShow(List<User> users) {
        friendRequestListAdapter = new FriendRequestListAdapter(this, users);
        requestListView.setAdapter(friendRequestListAdapter);
    }

    @Override
    public void onFriendRequestUpdate() {
        friendRequestListAdapter.notifyDataSetChanged();
    }


    class FriendRequestListAdapter extends BaseAdapter {
        Context context;
        private LayoutInflater inflater=null;
        List<User> users;

        public FriendRequestListAdapter(Context context, List<User> users) {
            this.context = context;
            this.users = users;
            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class Holder
        {
            ImageView ivAvatar;
            TextView tvNickname;
            Button btnAdd;
            Button btnDelete;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder = new Holder();;
            View rootView = inflater.inflate(R.layout.friend_request_item, parent,false);

            holder.ivAvatar = (ImageView)rootView.findViewById(R.id.iv_avatar);
            holder.tvNickname = (TextView)rootView.findViewById(R.id.tv_nickname);
            holder.btnAdd = (Button)rootView.findViewById(R.id.btn_add);
            holder.btnDelete = (Button)rootView.findViewById(R.id.btn_delete);

            holder.ivAvatar.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(users.get(position).getHeadImgPath(), "drawable", getPackageName())));
            holder.tvNickname.setText(users.get(position).getNickname());

            holder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    friendRequestPresenter.doFriendAgree(position);
                }
            });

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    friendRequestPresenter.doFriendDisagree(position);
                }
            });

            return rootView;
        }
    }

}
