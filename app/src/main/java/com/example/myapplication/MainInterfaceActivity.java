package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.shashank.sony.fancytoastlib.FancyToast;

public class MainInterfaceActivity extends AppCompatActivity implements View.OnClickListener {

    public WebView mainWebView;
    private ImageButton btn_HomeM,btn_ProfileM,btn_PaymentM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
        btn_HomeM = findViewById(R.id.imageButton5);
        btn_HomeM.setOnClickListener(this);
        btn_ProfileM = findViewById(R.id.imageButton6);
        btn_ProfileM.setOnClickListener(this);

        mainWebView =(WebView)findViewById(R.id.mainWebView);
        mainWebView.getSettings().setLoadsImagesAutomatically(true);
        mainWebView.getSettings().setJavaScriptEnabled(true);
        mainWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mainWebView.setWebViewClient(new WebViewClient());
        mainWebView.loadUrl("http://technohubbit.pythonanywhere.com/");

    }

    @Override
    public void onBackPressed() {
        if(mainWebView.canGoBack()){
            mainWebView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.imageButton5:
                    FancyToast.makeText(MainInterfaceActivity.this, "You are in our Website", FancyToast.INFO, FancyToast.LENGTH_LONG, true).show();
                    break;
                case R.id.imageButton6:
                  moveTransitionToLayout();
                    break;

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public  void moveTransitionToLayout(){
        Intent intent = new Intent (MainInterfaceActivity.this,UserrofileActivity.class);
        startActivity(intent);
    }
    public  void moveTransitionToLayoutPay(){
        Intent intentP = new Intent(MainInterfaceActivity.this,PaymentActivity.class);
        startActivity(intentP);
    }
}
