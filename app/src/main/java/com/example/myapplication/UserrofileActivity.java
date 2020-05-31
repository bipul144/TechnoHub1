package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class UserrofileActivity extends AppCompatActivity implements View.OnClickListener {


    private CircularImageView profileImg;
    private Uri mCropImageUri;
    private ImageButton btn_HomeP,btn_ProfileP,btn_PaymentP;
    private Button btn_Logout;
    private TextView profileName,profileEmail,profileBranch,profileField;
private String parseID;
    private String username;
    private String email;
    private java.util.Date Date;
    private static final int Pick_Image = 1;
    private Uri ImageUri;




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userrofile);

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileBranch = findViewById(R.id.profileBranch);
        profileField = findViewById(R.id.profileField);



        ParseUser pUser= ParseUser.getCurrentUser();
        if (pUser != null){
             email = pUser.getEmail();
             profileEmail.setText(email);
             username = pUser.getUsername();
             profileName.setText(username);
             parseID = pUser.getObjectId();
             profileField.setText(parseID);
             Date = pUser.getCreatedAt();
             profileBranch.setText(Date+"");
        }



        profileImg =findViewById(R.id.profileImg);
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openImageFromGallery();

            }
        });
        btn_HomeP = findViewById(R.id.btn_HomeP);
        btn_ProfileP = findViewById(R.id.btn_ProfileP);
        btn_PaymentP = findViewById(R.id.btn_PaymentP);
       profileName = findViewById(R.id.profileName);
       profileEmail = findViewById(R.id.profileEmail);
       profileBranch = findViewById(R.id.profileBranch);
       profileField = findViewById(R.id.profileField);
       btn_HomeP.setOnClickListener(this);
       btn_ProfileP.setOnClickListener(this);
       btn_PaymentP.setOnClickListener(this);
       btn_Logout = findViewById(R.id.btn_Logout);
       btn_Logout.setOnClickListener(this);





    }
    public void openImageFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,Pick_Image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Pick_Image && resultCode == RESULT_OK && data !=null && data.getData() !=null){
            ImageUri = data.getData();
            profileImg.setImageURI(ImageUri);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_HomeP:
                moveTransitionToLayout();
              break;

            case R.id.btn_ProfileP:
                FancyToast.makeText(UserrofileActivity.this,"You are in Your Profile ",FancyToast.INFO,FancyToast.LENGTH_LONG,true).show();
                break;
            case R.id.btn_PaymentP:
                moveTransitionToLayoutPay();
                break;
            case R.id.btn_Logout:
                    moveToLogout();
                    break;


        }

    }


    public  void moveTransitionToLayout(){
        Intent intent = new Intent(UserrofileActivity.this,MainInterfaceActivity.class);
        startActivity(intent);
    }
    public  void moveTransitionToLayoutPay(){
        Intent intentP = new Intent(UserrofileActivity.this,PaymentActivity.class);
        startActivity(intentP);
    }
    public void moveToLogout(){
        AlertDialog.Builder mBuilderF = new AlertDialog.Builder(UserrofileActivity.this);
        mBuilderF.setTitle("LogOut");
        mBuilderF.setIcon(R.drawable.tech);
        mBuilderF.setMessage("Do You want To Logout ?");
        mBuilderF.setPositiveButton("LogOut", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ParseUser.getCurrentUser().logOut();
                Intent intent= new Intent(UserrofileActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mBuilderF.setNegativeButton("Cancel",null);
        AlertDialog dialog = mBuilderF.create();
        dialog.show();
        dialog.setCancelable(false);


    }
}
//Bipul Biswas nanditabiswas9009@oksbi