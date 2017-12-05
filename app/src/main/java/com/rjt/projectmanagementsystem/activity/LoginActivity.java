package com.rjt.projectmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rjt.projectmanagementsystem.R;
import com.rjt.projectmanagementsystem.model.Account;
import com.rjt.projectmanagementsystem.model.UserInfo;
import com.rjt.projectmanagementsystem.network.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email)
    TextInputEditText _emailText;
    @BindView(R.id.input_password) TextInputEditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;
    @BindView(R.id.link_forgot)
    TextView tvForgot;

    Util mUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mUtil=new Util();
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPwd();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), com.rjt.projectmanagementsystem.activity.SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    private void forgotPwd() {
        String email = _emailText.getText().toString();

        mUtil.forgetPassword(email, new Util.ForgetPasswordCallback() {
            @Override
            public void onResponse(Account account) {
                Log.i(TAG,"Password is "+account.getUserpassword().toString());
                Toast.makeText(LoginActivity.this,account.getUserpassword().toString() , Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void login() {
        if (!validate()) { //check if it's valid
            onLoginFailed();
            return;
        }
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        mUtil.login(email, password, new Util.LoginCallback() {
            @Override
            public void onResponse(UserInfo info) {
                Log.i(TAG,info.toString());
                //Toast.makeText(LoginActivity.this,info.toString() , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


       /* ApiService APIService = ApiClient.getClient().create(ApiService.class);
        Call<User> loginResponseCall = APIService.getLoginResponse(email, password);

        loginResponseCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i(TAG,response.body().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                Log.i(TAG, t.toString());
            }
        });*/
    }

    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter an email");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}

