package com.rjt.projectmanagementsystem.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.malinkang.rxvalidator.RxValidator;
import com.malinkang.rxvalidator.ValidationFail;
import com.malinkang.rxvalidator.ValidationResult;
import com.malinkang.rxvalidator.annotations.MaxLength;
import com.malinkang.rxvalidator.annotations.MinLength;
import com.malinkang.rxvalidator.annotations.NotEmpty;
import com.malinkang.rxvalidator.annotations.RegExp;
import com.rjt.projectmanagementsystem.R;
import com.rjt.projectmanagementsystem.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SignupActivity";

    @NotEmpty(order = 1, message = "FirstName cannot be empty")
    @MinLength(order = 12, length =3 , message = "minimum lenght is 3")
    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.firstNameLayout)
    TextInputLayout firstNameTextInputLayout;

    @NotEmpty(order = 2, message = "LastName cannot be empty")
    @MinLength(order = 13, length = 3, message = "minimum lenght is 3")
    @BindView(R.id.input_last_name)
    EditText _lastnameText;
    @BindView(R.id.lastNameLayout)
    TextInputLayout lastNameTextInputLayout;

    @NotEmpty(order = 10, message = "Email cannot be empty")
    //@RegExp(order = 11, message = "enter valid email", regexp = "?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\]")
    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.emailLayout)
    TextInputLayout emailLayout;

    @NotEmpty(order = 3, message = "mobile cannot be empty")
    @RegExp(order = 4, message = "enter valid number", regexp = "^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}")
    @BindView(R.id.input_mobile)
    EditText _mobileText;
    @BindView(R.id.phoneLayout)
    TextInputLayout phoneTextInputLayout;

    @NotEmpty(order = 5, message = "password cannot be empty")
    @MinLength(order = 6, length = 6, message = "minimum lenght is 6")
    @MaxLength(order = 7, length = 12, message = "max length is 12")
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.passwordLayout)
    TextInputLayout passwordTextInputLayout;

    @NotEmpty(order = 8, message = "Company Size cannot be empty")
    @BindView(R.id.company_size)
    EditText _companySize;
    @BindView(R.id.companySizeLayout)
    TextInputLayout companySizeLayout;

    @NotEmpty(order = 9, message = "Role cannot be empty")
    @BindView(R.id.company_role)
    EditText _companyRole;
    @BindView(R.id.companyRoleLayout)
    TextInputLayout companyRoleLayout;

    /*@Bind(R.id.input_reEnterPassword)
    EditText _reEnterPasswordText;*/
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;

    Map<EditText, TextInputLayout> inputLayoutMap = new HashMap<>();

    Util util;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        inputLayoutMap.put(_nameText, firstNameTextInputLayout);
        inputLayoutMap.put(_lastnameText, lastNameTextInputLayout);
        inputLayoutMap.put(_emailText,emailLayout );
        inputLayoutMap.put(_mobileText, phoneTextInputLayout);
        inputLayoutMap.put(_passwordText, passwordTextInputLayout);
        inputLayoutMap.put(_companySize, companySizeLayout);
        inputLayoutMap.put(_companyRole, companyRoleLayout);


        _signupButton.setOnClickListener(this);

        util = new Util();
    }

    Subscription subscription;
    private boolean isValid;

    @Override
    public void onClick(View v) {

        if (subscription == null || subscription.isUnsubscribed()) {
            subscription = RxValidator.validate(this).subscribe(new Action1<ValidationResult>() {
                @Override
                public void call(ValidationResult validationResult) {
                    isValid = validationResult.isValid;
                    for (EditText editText : inputLayoutMap.keySet()) {
                        TextInputLayout textInputLayout = inputLayoutMap.get(editText);
                        textInputLayout.setErrorEnabled(false);
                    }
                    if (!validationResult.isValid) {
                        ArrayList<ValidationFail> errors = validationResult.getFails();
                        for (ValidationFail fail : errors) {
                            TextInputLayout textInputLayout = inputLayoutMap.get(fail.getView());
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError(fail.getMessage());
                        }
                    }
                }
            });
        }
        if (isValid) {
            Toast.makeText(SignupActivity.this, "Register Success...", Toast.LENGTH_SHORT).show();
            signup();
        }
    }

    private void signup() {

        Log.d(TAG, "Signup");
        //    _signupButton.setEnabled(false);

        String first_name = _nameText.getText().toString();
        String last_name=_lastnameText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String companySize = _companySize.getText().toString();
        String companyRole = _companyRole.getText().toString();


        util.register(first_name, last_name, email, mobile, password, companySize, companyRole, new Util.RegisterCallback() {
            @Override
            public void onResponse(String  response) {

                Toast.makeText(SignupActivity.this,response.toString() , Toast.LENGTH_SHORT).show();
                Log.i(TAG, "get response success, "+response.toString());
                finish();
            }
        });

    }

    private void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }


}
