package com.starun.www.starun.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.AddFriendPresenter;
import com.starun.www.starun.presenter.impl.AddFriendPresenterImpl;
import com.starun.www.starun.pview.AddFriendView;
import com.starun.www.starun.server.data.User;
import com.starun.www.starun.view.application.MyApplication;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SearchFriendActivity extends AppCompatActivity implements AddFriendView {
    private SearchView sv = null;
    private TextView tvFriend, tvStranger,tvNoFriend, tvNoStranger, toback;
    private ListView lvFriend, lvStranger;
    private SearchFriendAdapter searchFriendAdapter;
    private SearchStrangerAdapter searchStrangerAdapter;
    private ArrayList<User> friendsItems;
    private AddFriendPresenter addFriendPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_search_friend);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvFriend = (TextView) findViewById(R.id.tv_search_friend);
        tvStranger = (TextView) findViewById(R.id.tv_search_stranger);
        tvNoFriend = (TextView) findViewById(R.id.tv_no_friend);
        toback = (TextView) findViewById(R.id.tv_search_friend_back);
        tvNoStranger = (TextView) findViewById(R.id.tv_no_Stranger);
        lvFriend = (ListView) findViewById(R.id.lv_search_friend);
        lvStranger = (ListView) findViewById(R.id.lv_search_stranger);
        tvStranger.setVisibility(View.GONE);
        tvFriend.setVisibility(View.GONE);
        lvStranger.setVisibility(View.GONE);
        lvFriend.setVisibility(View.GONE);
        tvNoFriend.setVisibility(View.GONE);
        tvNoStranger.setVisibility(View.GONE);
        addFriendPresenter = new AddFriendPresenterImpl(this);
        sv = (SearchView) this.findViewById(R.id.sv_search_friend);
        sv.setIconifiedByDefault(false);
        sv.setSubmitButtonEnabled(true);
        sv.setQueryHint("请输入好友账号");

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String str) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String str) {

                int user_id = ((MyApplication) getApplicationContext()).getUser().getUser_id();
                addFriendPresenter.getSearchResultList(str, user_id);
                return false;
            }

        });

        toback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchFriendActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void showSearchResultList(List<User> friendList, List<User> unFriendList) {
        if(friendList.size()==0){
            tvNoFriend.setVisibility(View.VISIBLE);
        }else {
            lvFriend.setVisibility(View.VISIBLE);
            searchFriendAdapter = new SearchFriendAdapter(SearchFriendActivity.this, friendList); //创建适配器
            lvFriend.setAdapter(searchFriendAdapter);
        }
        if(unFriendList.size()==0) {
            tvNoStranger.setVisibility(View.VISIBLE);
        }else {
            lvStranger.setVisibility(View.VISIBLE);
            searchStrangerAdapter = new SearchStrangerAdapter(SearchFriendActivity.this, unFriendList, addFriendPresenter);
            lvStranger.setAdapter(searchStrangerAdapter);
        }
        tvStranger.setVisibility(View.VISIBLE);
        tvFriend.setVisibility(View.VISIBLE);

    }

    @Override
    public void addFriend() {
        //并没有什么用
    }

    @Override
    public void showError() {
        new AlertDialog.Builder(SearchFriendActivity.this).setTitle("提示").setMessage("出错啦！").setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    @Override
    public Activity getActivity() {
        return SearchFriendActivity.this;
    }

    public class SearchStrangerAdapter extends BaseAdapter {
        private List<User> strangers;
        private Context context;
        private LayoutInflater listContainer;
        private AddFriendPresenter addFriendPresenter;
        private SearchStrangerListItemView searchStrangerListItemView = null;

        public final class SearchStrangerListItemView {
            public ImageView image;
            public TextView name;
            public TextView add;

        }

        SearchStrangerAdapter(Context context, List<User> strangerslist, AddFriendPresenter presenter) {
            strangers = strangerslist;
            this.context = context;
            listContainer = LayoutInflater.from(context);
            addFriendPresenter = presenter;
        }

        @Override
        public int getCount() {
            return strangers.size();
        }

        @Override
        public Object getItem(int position) {
            return strangers.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                searchStrangerListItemView = new SearchStrangerListItemView();
                convertView = listContainer.inflate(R.layout.stranger_listview_item, null);
                searchStrangerListItemView.image = (ImageView) convertView.findViewById(R.id.stranger_list_icon);
                searchStrangerListItemView.name = (TextView) convertView.findViewById(R.id.stranger_list_name);
                searchStrangerListItemView.add = (TextView) convertView.findViewById(R.id.stranger_list_add);
                convertView.setTag(searchStrangerListItemView);

            } else {
                searchStrangerListItemView = (SearchStrangerListItemView) convertView.getTag();
            }
            searchStrangerListItemView.image.setImageURI(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() +
                    strangers.get(position).getHeadImgPath()));

            searchStrangerListItemView.image.setBackgroundResource(R.drawable.icon1);
            searchStrangerListItemView.name.setText(strangers.get(position).getNickname());
            searchStrangerListItemView.add.setOnClickListener(new tvListener(convertView, searchStrangerListItemView, position));

            return convertView;
        }

        class tvListener implements View.OnClickListener {
            private View convertView;
            private SearchStrangerListItemView item;
            private int location;

            tvListener(View convertView, SearchStrangerListItemView item, int location) {
                this.convertView = convertView;
                this.item = item;
                this.location = location;
            }

            @Override
            public void onClick(View v) {
                int user_id = ((MyApplication) getApplicationContext()).getUser().getUser_id();
                /////////////////////////////////////////////添加朋友在这儿
                addFriendPresenter.addFriend(user_id + "", strangers.get(location).getUser_id() + "");
                item.add.setText("等待确认");
                item.add.setBackgroundColor(Color.TRANSPARENT);
                convertView.setTag(item);
            }
        }
    }


    public class SearchFriendAdapter extends BaseAdapter {
        private List<User> friends;
        private Context context;
        private LayoutInflater listContainer;

        public final class SearchFriendListItemView {
            public ImageView image;
            public TextView name;
        }

        SearchFriendAdapter(Context context, List<User> friendslist) {
            friends = friendslist;
            this.context = context;
            listContainer = LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            return friends.size();
        }

        @Override
        public Object getItem(int position) {
            return friends.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SearchFriendListItemView searchFriendListItemView = null;
            if (convertView == null) {
                searchFriendListItemView = new SearchFriendListItemView();
                convertView = listContainer.inflate(R.layout.friend_listview_item, null);
                searchFriendListItemView.image = (ImageView) convertView.findViewById(R.id.friend_list_icon);
                searchFriendListItemView.name = (TextView) convertView.findViewById(R.id.friend_list_name);
                convertView.setTag(searchFriendListItemView);

            } else {
                searchFriendListItemView = (SearchFriendListItemView) convertView.getTag();
            }
//        searchFriendListItemView.image.setImageURI(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() +
//                friends.get(position).getHeadImgPath()));
            searchFriendListItemView.image.setBackgroundResource(R.drawable.icon1);
            searchFriendListItemView.name.setText(friends.get(position).getNickname());
            return convertView;
        }

    }
}
