package com.example.myapplication;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("tHtHDLJ3F6sLMU7F60evabp0jObpApetNdH1fjRl")
                // if defined
                .clientKey("baBtQu1qcp9UzPGlySWjzw4mZUNPaO5hcKbOkpn4")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
