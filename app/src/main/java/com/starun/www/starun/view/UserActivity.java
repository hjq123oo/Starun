package com.starun.www.starun.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.starun.www.starun.R;

public class UserActivity extends AppCompatActivity {

    private enum UserRelation{
        OWN,FRIEND,NON_FRIEND
    }

    private UserRelation userRelation;

    private Button btnTool;
    private LinearLayout ownLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        init();

    }


    private void init(){
        btnTool = (Button)findViewById(R.id.btn_tool);
        ownLinearLayout = (LinearLayout)findViewById(R.id.linearLayout_own);


        userRelation = UserRelation.NON_FRIEND;
        userRelation = UserRelation.FRIEND;
        userRelation = UserRelation.OWN;
        if(userRelation == UserRelation.OWN){
            initLayoutForOwn();
        }else if(userRelation == UserRelation.NON_FRIEND){
            initLayoutForNonFriend();
        }else if(userRelation == UserRelation.FRIEND){
            initLayoutForFriend();
        }
    }

    private void initLayoutForOwn(){
        btnTool.setText("头像");
        ownLinearLayout.setVisibility(View.VISIBLE);

        btnTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,AvatarActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initLayoutForFriend(){
        btnTool.setText("删除好友");
        ownLinearLayout.setVisibility(View.GONE);
    }


    private void initLayoutForNonFriend(){
        btnTool.setText("添加好友");
        ownLinearLayout.setVisibility(View.GONE);
    }
}
