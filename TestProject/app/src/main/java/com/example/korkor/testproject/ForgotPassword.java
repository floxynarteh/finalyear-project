package com.example.korkor.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPassword extends AppCompatActivity {

    EditText oldPassword, newPassword;
    Button passwordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        oldPassword =(EditText)findViewById(R.id.fpassword);
        newPassword= (EditText)findViewById(R.id.cPassword);
        passwordBtn = (Button)findViewById(R.id.changePasswordBtn);
    }
}
