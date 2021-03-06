package com.starun.www.starun.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.starun.www.starun.R;
import com.starun.www.starun.presenter.UserPresenter;
import com.starun.www.starun.presenter.impl.UserPresenterImpl;
import com.starun.www.starun.pview.UserView;
import com.starun.www.starun.server.data.User;

/**
 * Created by yearsj on 2016/5/1.
 */
public class LoginActivity extends Activity implements UserView{

    private Button login = null;
    private EditText username = null;
    private EditText password = null;
    private UserPresenter userPresenter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button)findViewById(R.id.login);
        username = (EditText)findViewById(R.id.user_name);
        password = (EditText)findViewById(R.id.password);
        userPresenter = new UserPresenterImpl(this);

        username.addTextChangedListener(textWatcher );
        password.addTextChangedListener(textWatcher);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUsername(username.getText().toString());
                user.setPassword(username.getText().toString());
                userPresenter.login(user);
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!username.getText().equals("")&&!password.getText().equals("")){
                login.setEnabled(true);
            }
            else{
                login.setEnabled(false);
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
    public void onSuccess() {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this,ExerciseActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailure(String response) {
        Toast.makeText(this.getActivity(),"登陆失败，请确定用户名或密码是否正确",Toast.LENGTH_LONG).show();
    }
}
