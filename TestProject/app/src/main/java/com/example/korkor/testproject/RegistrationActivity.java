package com.example.korkor.testproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {


    private EditText fName, lName, eMail, phoneNumber, et_password, confirmPassword, et_Age;
    private String name, surname, phNumber, email, password, cPassword,etAge;
    private Button bRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fName =(EditText)findViewById(R.id.name);
        lName = (EditText)findViewById(R.id.surname);
        et_Age = (EditText)findViewById(R.id.etAge);
        eMail= (EditText)findViewById(R.id.email);
        phoneNumber = (EditText)findViewById(R.id.phNumber);
        et_password = (EditText)findViewById(R.id.password);
        confirmPassword = (EditText)findViewById(R.id.cPassword);
        bRegister = (Button) findViewById(R.id.registerBtn);

        mAuth = FirebaseAuth.getInstance();

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                initialize();
                register();



            }
        });


    }



    public void register(){
        initialize();

        if (!validate()) {
            Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
        } else {

//            onSignupSuccess();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Intent logIn = new Intent(RegistrationActivity.this, MainActivity.class);
                        startActivity(logIn);


                    }
                }
            });

        }
    }
//    public void onSignupSuccess(){
//
//
//    }

    public boolean validate() {
        boolean valid = true;
        if (name.isEmpty() || name.length() > 15) {
            fName.setError("Please enter your first name");
            valid = false;
        }
        if (surname.isEmpty() || name.length() > 15) {
            lName.setError("Please enter your surname");
            valid = false;
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            eMail.setError("Please enter valid email");
            valid = false;
        }
        if (password.isEmpty() || password.length()< 5) {
            et_password.setError("Please Enter password");
            valid = false;
        }

        if (cPassword.isEmpty() || cPassword.length()< 5) {
            confirmPassword.setError("Please Enter password");
            valid = false;
        }

        if (!password.equals(cPassword)) {
            confirmPassword.setError("Please Enter your password again");
            valid = false;
        }

        if (etAge.isEmpty() ) {
            et_Age.setError("Please Enter your Age");
            valid = false;
        }

        if (phNumber.isEmpty() || phNumber.length()> 10) {
            phoneNumber.setError("Please Enter your Phone Number");
            valid = false;
        }

        return valid;
    }

    private void initialize () {
        name= fName.getText().toString().trim();
        surname = lName.getText().toString().trim();
        phNumber = phoneNumber.getText().toString().trim();
        email = eMail.getText().toString().trim();
        etAge = et_Age.getText().toString().trim();
        password = et_password.getText().toString().trim();
        cPassword = confirmPassword.getText().toString().trim();

    }


}
