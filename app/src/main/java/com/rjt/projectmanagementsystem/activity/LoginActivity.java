package com.rjt.projectmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rjt.projectmanagementsystem.R;
import com.rjt.projectmanagementsystem.model.Account;
import com.rjt.projectmanagementsystem.model.UserInfo;
import com.rjt.projectmanagementsystem.model.firebase_model.User;
import com.rjt.projectmanagementsystem.posts.activity.BaseActivity;
import com.rjt.projectmanagementsystem.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    public static GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String userEmail;
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
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
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
        userEmail = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        mUtil.login(userEmail, password, new Util.LoginCallback() {
            @Override
            public void onResponse(UserInfo info) {
       //         tryRegisterFirebaseAccountRegistered();
                Log.i(TAG,info.toString());
                //Toast.makeText(LoginActivity.this,info.toString() , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("userEmail",userEmail);
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

    private void tryRegisterFirebaseAccountRegistered(){
        showProgressDialog();
        mAuth.createUserWithEmailAndPassword(userEmail, userEmail)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                       // hideProgressDialog();

                        if (task.isSuccessful()) {
                            //register firebase user.
                            onAuthSuccess(task.getResult().getUser());
                            Toast.makeText(LoginActivity.this, "Firebase account created", Toast.LENGTH_SHORT).show();
                            hideProgressDialog();
                        } else {
                            Log.i(TAG, "Firebase is registered before!");
                    //        mAuth.signInWithEmailAndPassword(userEmail, userEmail);
                            signInFirebaseAccount();
                        }
                    }
                });
    //    return false;
    }

    private void signInFirebaseAccount() {
        mAuth.signInWithEmailAndPassword(userEmail, userEmail)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signIn:onComplete:" + task.isSuccessful());
                     //   hideProgressDialog();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                          //  Toast.makeText(LoginActivity.this, "Firebase account signed in", Toast.LENGTH_SHORT).show();
                            Log.i(TAG,"Firebase account signed in!");

                        } else {
                            Log.i(TAG, "Firebase account failed to sign in");
                           // hideProgressDialog();
                        }
                    }
                });
        hideProgressDialog();
    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());

        // Go to MainActivity
    //    startActivity(new Intent(SignInActivity.this, MainActivity.class));
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

