package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class signupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText SignUpName,SignUpEmail,SignUpPassword;

    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setTitle("Sign Up");

        SignUpName = findViewById(R.id.InfoName);
        SignUpEmail = findViewById(R.id.InfoEmail);

        SignUpPassword = findViewById(R.id.ProjectInfo);


        btnSignUp = findViewById(R.id.btnInfo);
        btnSignUp.setOnClickListener(this);
        SignUpName.setOnClickListener(this);
        SignUpEmail.setOnClickListener(this);
        SignUpPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btnInfo:
                    if (SignUpName.getText().toString().equals("") ||
                            SignUpEmail.getText().toString().equals("") ||
                            SignUpPassword.getText().toString().equals("")) {
                        Message("Please Fill All The Field Properly !!", "E");

                    } else {
                        final ParseUser parseUser = new ParseUser();
                        parseUser.setEmail(SignUpEmail.getText().toString());
                        parseUser.setUsername(SignUpName.getText().toString());
                        parseUser.setPassword(SignUpPassword.getText().toString());

                        final ProgressDialog progressDialog = new ProgressDialog(signupActivity.this);
                        progressDialog.setMessage("Please Wait ...");
                        progressDialog.show();
                        parseUser.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Message(parseUser.getUsername() + " Welcome To TechnoHub", "S");
                                    moveToActivity();
                                    Message("Please Login Again", "I");


                                } else {
                                    Message(e.getMessage(), "E");
                                }
                                progressDialog.setCancelable(false);
                                progressDialog.dismiss();
                            }
                        });
                    }

                    break;
                case R.id.InfoName:
                    Message("Please Enter Your Name", "I");
                    break;
                case R.id.InfoEmail:
                    Message("Please Enter Your E-Mail", "I");
                    break;
                case R.id.ProjectInfo:
                    Message("Please Enter Your Password", "I");
                    break;

            }}catch (Exception e){
            Message(e.getMessage(),"E");
        }

    }



    public void Message(String Message, String act){
        switch (act){
            case "S":
                FancyToast.makeText(signupActivity.this,Message,FancyToast.SUCCESS,FancyToast.LENGTH_LONG,true).show();
                break;
            case "E":
                FancyToast.makeText(signupActivity.this,Message,FancyToast.ERROR,FancyToast.LENGTH_LONG,true).show();
                break;
            case "I":
                FancyToast.makeText(signupActivity.this,Message,FancyToast.INFO,FancyToast.LENGTH_LONG,true).show();
                break;
        }

    }
    public void moveToActivity(){
        Intent intent = new Intent(signupActivity.this,MainActivity.class);
        startActivity(intent);
    }
}

