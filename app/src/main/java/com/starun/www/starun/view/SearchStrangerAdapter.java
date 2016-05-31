package com.starun.www.starun.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.AddFriendPresenter;
import com.starun.www.starun.server.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hello on 2016/5/30.
 */
public class SearchStrangerAdapter  extends BaseAdapter {
    private List<User> strangers;
    private Context context;
    private LayoutInflater listContainer;
    private AddFriendPresenter addFriendPresenter;
    private SearchStrangerListItemView searchStrangerListItemView = null;
    public final class SearchStrangerListItemView{
        public ImageView image;
        public TextView name;
        public TextView add;

    }
    SearchStrangerAdapter(Context context,List<User> strangerslist,AddFriendPresenter presenter){
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

        if(convertView==null){
            searchStrangerListItemView = new SearchStrangerListItemView();
            convertView = listContainer.inflate(R.layout.stranger_listview_item, null);
            searchStrangerListItemView.image = (ImageView) convertView.findViewById(R.id.stranger_list_icon);
            searchStrangerListItemView.name = (TextView) convertView.findViewById(R.id.stranger_list_name);
            searchStrangerListItemView.add = (TextView) convertView.findViewById(R.id.stranger_list_add);
            convertView.setTag(searchStrangerListItemView);

        }
        else {
            searchStrangerListItemView = (SearchStrangerListItemView) convertView.getTag();
        }
        //searchFriendListItemView.image.setBackgroundResource(friends.get(position).getHeadImgPath());
        //
        //
        //
        searchStrangerListItemView.image.setBackgroundResource(R.drawable.pointer);
        searchStrangerListItemView.name.setText(strangers.get(position).getNickname());
        searchStrangerListItemView.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchStrangerListItemView.add.setText("等待确认");
                searchStrangerListItemView.add.setBackgroundColor(new Color().alpha(0));

                //addFriendPresenter.addFriend();
            }
        });
        return convertView;
    }

}
