package com.starun.www.starun.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.AvatarPresenter;
import com.starun.www.starun.presenter.impl.AvatarPresenterImpl;
import com.starun.www.starun.pview.AvatarView;
import com.starun.www.starun.view.application.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvatarActivity extends AppCompatActivity implements AvatarView{

    public static int[] avatars = { R.drawable.user_avatar_1, R.drawable.user_avatar_2, R.drawable.user_avatar_3,
            R.drawable.user_avatar_4, R.drawable.user_avatar_5, R.drawable.user_avatar_6, R.drawable.user_avatar_7,
            R.drawable.user_avatar_8, R.drawable.user_avatar_9,R.drawable.user_avatar_10,R.drawable.user_avatar_11,
            R.drawable.user_avatar_12,R.drawable.user_avatar_13,R.drawable.user_avatar_14};


    private GridView mGridView;

    private int width;

    private AvatarPresenter avatarPresenter;

    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        btnBack = (ImageButton)findViewById(R.id.btn_back);

        mGridView = (GridView)findViewById(R.id.gridView);


        width = getWindow().getWindowManager().getDefaultDisplay().getWidth();

        avatarPresenter = new AvatarPresenterImpl(this);

        mGridView.setAdapter(new AdapterGridView());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AvatarActivity.this,UserInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onAvatarUpload() {

        Intent intent = new Intent(AvatarActivity.this,UserInfoActivity.class);
        startActivity(intent);
        finish();
    }


    class AdapterGridView extends BaseAdapter {

        public int getCount() {
            // TODO Auto-generated method stub
            return avatars.length;
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return avatars[position];
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup arg2) {
            // TODO Auto-generated method stub

            if (convertView == null) {
                convertView = LayoutInflater.from(
                        AvatarActivity.this).inflate(
                        R.layout.avatar_gridview_item, null);
                ImageView mImageView = (ImageView) convertView
                        .findViewById(R.id.img_view_avatar);


                mImageView.setImageResource(avatars[position]);

                LinearLayout.LayoutParams mParams=(LinearLayout.LayoutParams) mImageView.getLayoutParams();
                mParams.weight=(width / 3);
                mParams.height=(width / 3);
                mImageView.setLayoutParams(mParams);
                mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String avatarFileName = "user_avatar_"+(position+1);
                        avatarPresenter.doAvatarUpload(avatarFileName);
                    }
                });
            }

            return convertView;
        }

    }

}
