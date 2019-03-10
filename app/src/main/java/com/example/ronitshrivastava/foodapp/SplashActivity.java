package com.example.ronitshrivastava.foodapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Boolean Registered;
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Registered = sharedPref.getBoolean("LoggedIn", false);

        if (!Registered)
        {
            startActivity(new Intent(this,Login_Page.class));
        }else {
            startActivity(new Intent(this,HomePage.class));
        }
    }
}
