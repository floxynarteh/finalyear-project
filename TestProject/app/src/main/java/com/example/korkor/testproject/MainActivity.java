package com.example.korkor.testproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    TextView registerLink, tvForgotPassword;
    Button logBtn;
    EditText userEmail,userPassword;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
//        userPassword =(EditText)findViewById(R.id.password);
//        userEmail = (EditText)findViewById(R.id.username);
        registerLink = (TextView) findViewById(R.id.tvRegisterLink);
        logBtn = (Button) findViewById(R.id.logInBtn);
        tvForgotPassword = (TextView) findViewById(R.id.forgotPasswordLink);
        mAuth = FirebaseAuth.getInstance();

//        if (mAuth.getCurrentUser() != null){
//            doLogin();
//            }


        usernameWrapper.setHint("Username");
        passwordWrapper.setHint("Password");

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //hideKeyboard();

                String userEmail = usernameWrapper.getEditText().getText().toString();
                String password = passwordWrapper.getEditText().getText().toString();

                if (!validateEmail(userEmail)) {
                    usernameWrapper.setError("Not a valid email address!");
                }else if (!validatePassword(password)) {
                    passwordWrapper.setError("Not a valid password!");
                } else {
                    usernameWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);
                    //doLogin();


                    mAuth.signInWithEmailAndPassword(userEmail, password)

                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        doLogin();
                                    }
                                     // startActivity(new Intent(getApplicationContext(),ContentPage.class));


                                    }

                            });
                }





//                String validatemail = "[a-zA-Z0-9\\+\\.\\_\\&\\-\\+] {1,256}"
//                        + "\\@" +
//                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
//                        "(" +
//                        "\\." +
//                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
//
//                        ")+";
//     String email=userEmail.getText().toString();
//                 Matcher matcher = Pattern.compile(validatemail).matcher(email);
//                 if(matcher.matches()){
//                     Toast.makeText(getApplicationContext(), "OK! I'm performing login.", Toast.LENGTH_SHORT).show();
//                     Intent intent = new Intent(MainActivity.this, ContentPage.class);
//                     startActivity(intent);
//                 }
//                else
//                    {
//                        userEmail.setError("Enter valid email");
//                    }
//                    userPassword.setError("Enter password");

            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }

        });


        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(intent);
            }

        });

    }

//    private void hideKeyboard() {
//        View view = getCurrentFocus();
//        if (view != null) {
//            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
//                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {

        return password.length() > 5;
    }

    public void doLogin() {
        Intent intent = new Intent(MainActivity.this, ContentPage.class);
        startActivity(intent);
//        //Toast.makeText(getApplicationContext(), "OK! I'm performing login.", Toast.LENGTH_SHORT).show();
//        // TODO: login procedure; not within the scope of this tutorial.

   }
}


