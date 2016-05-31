package com.starun.www.starun.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.AddFriendPresenter;
import com.starun.www.starun.presenter.impl.AddFriendPresenterImpl;
import com.starun.www.starun.pview.AddFriendView;
import com.starun.www.starun.server.data.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SearchFriendActivity extends AppCompatActivity implements AddFriendView{
    private SearchView sv = null;
    private  ListView lv = null;
    private TextView tvFriend,tvStranger;
    private ListView lvFriend,lvStranger;
    private SearchFriendAdapter searchFriendAdapter;
    private SearchStrangerAdapter searchStrangerAdapter;
    private ArrayList<User> friendsItems;
    private AddFriendPresenter addFriendPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        tvFriend = (TextView) findViewById(R.id.tv_search_friend);
        tvStranger = (TextView) findViewById(R.id.tv_search_stranger);
        lvFriend = (ListView) findViewById(R.id.lv_search_friend);
        lvStranger = (ListView) findViewById(R.id.lv_search_stranger);
        tvStranger.setVisibility(View.INVISIBLE);
        tvFriend.setVisibility(View.INVISIBLE);
        lvStranger.setVisibility(View.INVISIBLE);
        lvFriend.setVisibility(View.INVISIBLE);
        addFriendPresenter = new AddFriendPresenterImpl(this);
        sv = (SearchView) this.findViewById(R.id.sv_search_friend);
        sv.setIconifiedByDefault(false);
        sv.setSubmitButtonEnabled(true);
        sv.setQueryHint("查询");
        //通过反射，修改默认的样式，可以从android的search_view.xml中找到需要的组件
        try {
            Field field = sv.getClass().getDeclaredField("mSubmitButton");
            field.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String str) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String str) {
                addFriendPresenter.getSearchResultList(str);
//                tvStranger.setVisibility(View.VISIBLE);
//                tvFriend.setVisibility(View.VISIBLE);
//                lvStranger.setVisibility(View.VISIBLE);
//                lvFriend.setVisibility(View.VISIBLE);
//                friendsItems = new ArrayList<User>();
//                User user = new User();
//                user.setNickname("Lapidary");
//                friendsItems.add(user);
//                user = new User();
//                user.setNickname("fish");
//                friendsItems.add(user);
//                searchFriendAdapter = new SearchFriendAdapter(SearchFriendActivity.this, friendsItems); //创建适配器
//                lvFriend.setAdapter(searchFriendAdapter);
//                searchStrangerAdapter = new SearchStrangerAdapter(SearchFriendActivity.this,friendsItems);
//                lvStranger.setAdapter(searchStrangerAdapter);
                // tvFriend.setAdapter(new ArrayAdapter<String>(this, R.layout.friend_listview_item, R.id.itemtext, getResources().getStringArray(R.array.countries_arry)));
                return false;
            }

        });

    }

    @Override
    public void showSearchResultList(List<User> friendList, List<User> unFriendList) {
        tvStranger.setVisibility(View.VISIBLE);
        tvFriend.setVisibility(View.VISIBLE);
        lvStranger.setVisibility(View.VISIBLE);
        lvFriend.setVisibility(View.VISIBLE);
        searchFriendAdapter = new SearchFriendAdapter(SearchFriendActivity.this, friendList); //创建适配器
        lvFriend.setAdapter(searchFriendAdapter);
        searchStrangerAdapter = new SearchStrangerAdapter(SearchFriendActivity.this,unFriendList,addFriendPresenter);
        lvStranger.setAdapter(searchStrangerAdapter);
    }

    @Override
    public void addFriend() {

    }

    @Override
    public void showError() {
        new  AlertDialog.Builder(SearchFriendActivity.this).setTitle("提示").setMessage("出错啦！").setPositiveButton("确定",
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
//    public Cursor getTestCursor() {
//        return  null;
//    }
}
