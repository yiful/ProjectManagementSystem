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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.rjt.projectmanagementsystem.R;
import com.rjt.projectmanagementsystem.model.Account;
import com.rjt.projectmanagementsystem.model.UserInfo;
import com.rjt.projectmanagementsystem.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    public static GoogleSignInClient mGoogleSignInClient;
    Unbinder unbinder;
    @BindView(R.id.input_email)
    TextInputEditText _emailText;
    @BindView(R.id.input_password) TextInputEditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;
    @BindView(R.id.link_forgot)
    TextView tvForgot;
    @BindView(R.id.btnGoogle)
    SignInButton btnGoogle;

    Util mUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //google sign in
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        unbinder=ButterKnife.bind(this);
        btnGoogle.setSize(SignInButton.SIZE_STANDARD);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });
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

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null) {
            Toast.makeText(this, "google account logged in", Toast.LENGTH_SHORT).show();
            Log.i("onStart","username " + account.getDisplayName());
            Log.i("onStart", "photo "+account.getPhotoUrl());
        }
       //updateUI(account);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
         //   Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
            googleLoginToMain(data);

        }
    }

    private void googleLoginToMain(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Toast.makeText(this, "Welcome! "+ account.getDisplayName(), Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("userName", account.getDisplayName());
            intent.putExtra("userEmail", account.getEmail());
            intent.putExtra("userImg", account.getPhotoUrl().toString());
            startActivity(intent);
        } catch (ApiException e) {
            e.printStackTrace();
        }
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
        final String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        mUtil.login(email, password, new Util.LoginCallback() {
            @Override
            public void onResponse(UserInfo info) {
                Log.i(TAG,info.toString());
                //Toast.makeText(LoginActivity.this,info.toString() , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("userEmail",email);
                startActivity(intent);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

