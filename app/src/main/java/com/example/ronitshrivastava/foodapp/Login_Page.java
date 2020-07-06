package com.example.ronitshrivastava.foodapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Page extends AppCompatActivity {
    EditText LoginEmail;
    EditText LoginPassword;
    DatabaseHelper db;
    Button loginButton;
    String bundleEmailAddress,bundleName;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);
        LoginEmail = (EditText) findViewById(R.id.LoginEmail);
        LoginPassword = (EditText)findViewById(R.id.LoginPassword);
        db = new DatabaseHelper(this);
        loginButton = (Button)findViewById(R.id.LoginButton);
        extras = getIntent().getExtras();
        if (extras != null) {
            bundleEmailAddress = extras.getString("emailAddress");
            bundleName=extras.getString("name");
            //The key argument here must match that used in the other activity
            //Toast.makeText(this, ""+value, Toast.LENGTH_SHORT).show();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = LoginEmail.getText().toString();
                String password = LoginPassword.getText().toString();
                Boolean chkemailpass = db.chkemailpassword(email, password);
                if(chkemailpass) {
                    Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();

                    //shared preferences
                    final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Login_Page.this);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("LoggedIn", true);
                    editor.putString("Email", email);
                    editor.putString("Password", password);
                    editor.apply();

                    //bundle
                    Intent i = new Intent(Login_Page.this, HomePage.class);
                    i.putExtra("name",bundleName);
                    i.putExtra("emailAddress",bundleEmailAddress);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(extras.getBoolean("isLoggedIn")==false)
            startActivity(new Intent(Login_Page.this, SplashActivity.class));
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void SignUpPage(View view) {
        startActivity(new Intent(Login_Page.this, Sign_up.class));
    }

}
