package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText LoginEmail,LoginPassword;
    private Button btnLogin;
    private TextView LinkToSignU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        LoginEmail = findViewById(R.id.LoginEmail);
        LoginPassword = findViewById(R.id.LoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode ==KeyEvent.KEYCODE_ENTER &&
                event.getAction() ==KeyEvent.ACTION_DOWN){
                    onClick(btnLogin);
                }
                return false;
            }
        });
        btnLogin.setOnClickListener(this);
        LinkToSignU  = findViewById(R.id.LinkToSignU);
        LinkToSignU.setOnClickListener(this);
        if(ParseUser.getCurrentUser() != null){
            Intent intentMain = new Intent(LogInActivity.this,MainInterfaceActivity.class);
            startActivity(intentMain);
        }
    }

    @Override
    public void onClick(View v) {
        try{
        switch(v.getId()){
            case R.id.btnLogin:
                if(LoginEmail.getText().toString().equals("")||
                LoginPassword.getText().toString().equals("")){
                    FancyToast.makeText(LogInActivity.this,"Please Fill The Entries Properly",
                            FancyToast.ERROR,FancyToast.LENGTH_LONG,true).show();

                }else {
                final ProgressDialog progressDialog = new ProgressDialog(LogInActivity.this);
                progressDialog.setMessage("Please Wait !!");
                progressDialog.show();

                ParseUser.logInInBackground(LoginEmail.getText().toString(), LoginPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(e==null && user!=null){
                            if(user.getBoolean("emailVerified")) {
                                alertDisplayer("Login Successful", "Welcome, " + user.getUsername() + ";)", false);
                            }else{
                                ParseUser.logOut();
                                alertDisplayer("Login Failed", "Please Verify Your Email First", true);
                            }
                        }else{
                            ParseUser.logOut();
                            alertDisplayer("Login Failed",e.getMessage() + " Please Retry", true);
                        }
                        progressDialog.setCancelable(false);
                        progressDialog.dismiss();
                    }
                });}

                break;
            case R.id.LinkToSignU:
                Intent intent = new Intent(LogInActivity.this,signupActivity.class);
                startActivity(intent);
                break;
        }}catch (Exception e){
            FancyToast.makeText(LogInActivity.this,e.getMessage(),FancyToast.ERROR,FancyToast.LENGTH_LONG,true).show();
        }
    }
    public void moveToSocialMedia(){
        Intent intentMain = new Intent(LogInActivity.this,MainInterfaceActivity.class);
        startActivity(intentMain);
        finish();
    }
    public void onBackPressed() {
        //do nothing

    }
    private void alertDisplayer(String title,String message,final boolean error){
        AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                if(!error){
                    Intent intent = new Intent(LogInActivity.this,MainInterfaceActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivity(intent);
                }
            }
        });
        AlertDialog Ok = builder.create();
        Ok.show();
        Ok.setCancelable(false);
    }
}
