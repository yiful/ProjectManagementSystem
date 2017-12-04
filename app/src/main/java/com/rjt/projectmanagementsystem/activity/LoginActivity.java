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
import com.rjt.projectmanagementsystem.model.ForgotPwdResponse;
import com.rjt.projectmanagementsystem.model.User;
import com.rjt.projectmanagementsystem.network.ApiClient;
import com.rjt.projectmanagementsystem.network.ApiService;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_email)
    TextInputEditText _emailText;
    @Bind(R.id.input_password) TextInputEditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;
    @Bind(R.id.link_signup)
    TextView _signupLink;
    @Bind(R.id.link_forgot)
    TextView tvForgot;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

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

        ApiService apiService=ApiClient.getClient().create(ApiService.class);
        Call<ForgotPwdResponse> forgotPwdResponseCall =apiService.getForgotPwdResponse(email);
        forgotPwdResponseCall.enqueue(new Callback<ForgotPwdResponse>() {
            @Override
            public void onResponse(Call<ForgotPwdResponse> call, Response<ForgotPwdResponse> response) {
                Log.i(TAG,"Password is "+response.body().getUserpassword().toString());
            }

            @Override
            public void onFailure(Call<ForgotPwdResponse> call, Throwable t) {

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

        // Api Call using Retrofit
        ApiService APIService = ApiClient.getClient().create(ApiService.class);
        Call<User> loginResponseCall = APIService.getLoginResponse(email, password);

        loginResponseCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i(TAG,response.body().toString());
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                Log.i(TAG, t.toString());
            }
        });
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

