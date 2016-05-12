package com.starun.www.starun.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.UserPresenter;
import com.starun.www.starun.presenter.impl.UserPresenterImpl;
import com.starun.www.starun.pview.UserView;
import com.starun.www.starun.server.data.User;

/**
 * Created by yearsj on 2016/5/1.
 */
public class RegisterActivity extends Activity implements UserView{

    private Button register = null;
    private EditText user_num = null;
    private EditText password1 = null;
    private EditText password2 = null;
    private EditText nickname = null;
    private ImageButton back = null;
    private UserPresenter userPresenter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button)findViewById(R.id.register);
        user_num = (EditText)findViewById(R.id.user_num);
        password1 = (EditText)findViewById(R.id.password1);
        password2 = (EditText)findViewById(R.id.password2);
        nickname = (EditText)findViewById(R.id.nickname);
        back = (ImageButton)findViewById(R.id.re_back_login);
        userPresenter = new UserPresenterImpl(this);

        user_num.addTextChangedListener(textWatcher );
        password1.addTextChangedListener(textWatcher);
        password2.addTextChangedListener(textWatcher);
        nickname.addTextChangedListener(textWatcher);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!password2.getText().toString().equals(password1.getText().toString())){
                    Toast.makeText(getActivity(),"请确定密码是否相同！",Toast.LENGTH_LONG).show();
                }
                else{
                    User user = new User();
                    user.setUsername(user_num.getText().toString());
                    user.setPassword(password1.getText().toString());
                    user.setNickname(nickname.getText().toString());
                    userPresenter.register(user);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent login = new Intent();
                login.setClass(RegisterActivity.this,LoginActivity.class);
                startActivity(login);
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String temp = user_num.getText().toString();
            if(!user_num.getText().toString().equals("")&&!password1.getText().toString().equals("")&&!password2.getText().toString().equals("")&&!nickname.getText().toString().equals("")){
                register.setEnabled(true);
            }
            else{
                register.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onSuccess(String msg) {
        if("".equals(msg)){
            Toast.makeText(this.getActivity(),msg,Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent();
        intent.setClass(RegisterActivity.this,LoginActivity.class);
        this.getActivity().startActivity(intent);
    }

    @Override
    public void onFailure() {
        Toast.makeText(this.getActivity(),"注册失败，该用户已注册",Toast.LENGTH_LONG).show();
    }
}
