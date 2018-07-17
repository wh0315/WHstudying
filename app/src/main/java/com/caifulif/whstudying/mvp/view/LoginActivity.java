package com.caifulif.whstudying.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.caifulif.whstudying.R;
import com.caifulif.whstudying.mvp.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements IloginView {

    private EditText u_name, u_pwd;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        u_name = (EditText) findViewById(R.id.u_name);
        u_pwd = (EditText) findViewById(R.id.u_pwd);
        presenter = new LoginPresenter(LoginActivity.this);
        findViewById(R.id.u_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(LoginActivity.this);
            }
        });

    }


    @Override
    public String getUsername() {
        return u_name.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return u_pwd.getText().toString();
    }
}
