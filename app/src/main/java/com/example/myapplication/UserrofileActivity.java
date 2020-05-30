package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import java.util.List;

public class UserrofileActivity extends AppCompatActivity implements View.OnClickListener {


    private CircularImageView profileImg;
    private Uri mCropImageUri;
    private ImageButton btn_HomeP,btn_ProfileP,btn_PaymentP;
    private Button btn_Logout;
    private TextView profileName,profileEmail,profileBranch,profileField;
private String parseID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userrofile);

        profileImg =(CircularImageView)findViewById(R.id.profileImg);
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectImageClick(v);

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
       btn_PaymentP.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               moveTransitionToLayoutPay();
           }
       });




    }

    public void onSelectImageClick(View view) {
        CropImage.startPickImageActivity(this);
    }
    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // handle result of pick image chooser
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            } else {
                // no permissions required or already grunted, can start crop image activity
                startCropImageActivity(imageUri);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                ((ImageButton) findViewById(R.id.profileImg)).setImageURI(result.getUri());
                Toast.makeText(this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // required permissions granted, start crop image activity
            startCropImageActivity(mCropImageUri);
        } else {
            Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Start crop image activity for the given image.
     */
    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_HomeP:

              moveTransitionToLayout();

                break;

            case R.id.btn_ProfileP:
                FancyToast.makeText(UserrofileActivity.this,"You are in Your Profile",FancyToast.INFO,FancyToast.LENGTH_LONG,true).show();

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
}
