package com.starun.www.starun.view.customview;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.FriendOrRankListPresenter;
import com.starun.www.starun.server.data.User;

import java.util.List;

/**
 * Created by yearsj on 2016/5/21.
 */
public class UserAdapter extends BaseAdapter {

    //自定义类
    private static class ViewHolder {
        ImageView portrait;
        TextView username;
        TextView detail;
        TextView no;
    }

    private Context context;
    private List<User> userList;
    private FriendOrRankListPresenter friendOrRankListPresenter = null;
    private int begin = 1;

    public UserAdapter(Context context, List<User> userList,FriendOrRankListPresenter friendOrRankListPresenter, int begin){
        this.context = context;
        this.userList= userList;
        this.friendOrRankListPresenter = friendOrRankListPresenter;
        this.begin = begin;
    }

    public UserAdapter(Context context, List<User> userList,FriendOrRankListPresenter friendOrRankListPresenter){
        this(context,userList,friendOrRankListPresenter,1);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        //return userList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.userlist, null);
            holder = new ViewHolder();
            holder.portrait = (ImageView) convertView.findViewById(R.id.img_view);
            holder.username = (TextView) convertView.findViewById(R.id.tex_name);
            holder.detail = (TextView) convertView.findViewById(R.id.tex_detail);
            holder.no = (TextView) convertView.findViewById(R.id.text_rank);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }


        holder.portrait.setImageDrawable(context.getResources().getDrawable(context.getResources().getIdentifier(userList.get(position).getHeadImgPath(), "drawable", context.getPackageName())));
        holder.username.setText(userList.get(position).getNickname());
        holder.no.setText(String.valueOf(position+begin));
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendOrRankListPresenter.showFriendDetail(userList.get(position));
            }
        });
        return convertView;
    }

}
