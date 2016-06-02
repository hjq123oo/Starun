package com.starun.www.starun.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.starun.www.starun.R;
import com.starun.www.starun.server.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hello on 2016/5/30.
 */
public class SearchFriendAdapter extends BaseAdapter {
    private  List<User> friends;
    private Context context;
    private LayoutInflater listContainer;
    public final class SearchFriendListItemView{
        public ImageView image;
        public TextView name;
    }
    SearchFriendAdapter(Context context,List<User> friendslist){
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
        if(convertView==null){
            searchFriendListItemView = new SearchFriendListItemView();
            convertView = listContainer.inflate(R.layout.friend_listview_item, null);
            searchFriendListItemView.image = (ImageView) convertView.findViewById(R.id.friend_list_icon);
            searchFriendListItemView.name = (TextView) convertView.findViewById(R.id.friend_list_name);
            convertView.setTag(searchFriendListItemView);

        }
        else {
            searchFriendListItemView = (SearchFriendListItemView) convertView.getTag();
        }
        searchFriendListItemView.image.setImageURI(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() +
                friends.get(position).getHeadImgPath()));
       // searchFriendListItemView.image.setBackgroundResource(R.drawable.icon1);
        searchFriendListItemView.name.setText(friends.get(position).getNickname());
        return convertView;
    }


}
