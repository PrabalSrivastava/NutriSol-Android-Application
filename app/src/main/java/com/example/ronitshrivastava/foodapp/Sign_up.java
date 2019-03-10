package com.example.ronitshrivastava.foodapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {
    TextInputLayout mSignupName;
    TextInputLayout mSignupEmail;
    TextInputLayout mSignupPassword;
    TextInputLayout mSignupConfirmPassword;
    CheckBox mSignupCheckbox;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mSignupName = findViewById(R.id.SignupName);
        mSignupEmail = findViewById(R.id.SignupEmail);
        mSignupPassword = findViewById(R.id.SignupPassword);
        mSignupConfirmPassword = findViewById(R.id.SignupConfirmPassword);
        mSignupCheckbox = findViewById(R.id.SignupCheckbox);
        db =new DatabaseHelper(this);

    }

    public boolean NameValidation() {
        String name = mSignupName.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            mSignupName.setError("This Field can't be Empty");
            return false;
        }
        return true;
    }

    public boolean EmailValidation() {
        String email = mSignupEmail.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            mSignupEmail.setError("Email can't be Empty");
            return false;
        } /*else if (!email.contains("@") | !email.contains(".")) {
            mSignupEmail.setError("Email not valid");
            return false;
        }*/
        return true;
    }

    public boolean PasswordValidation() {
        String pass = mSignupPassword.getEditText().getText().toString().trim();
        String ConfirmPass = mSignupConfirmPassword.getEditText().getText().toString().trim();
        if (pass.isEmpty()) {
            mSignupPassword.setError("Password Field can't be Empty");
            return false;
        } else if (pass.length() < 6) {
            mSignupPassword.setError("Password too Short");
            return false;
        } else if (!pass.equals(ConfirmPass)) {
            mSignupConfirmPassword.setError("Password doesn't match");
            return false;
        }
        return true;
    }

    public boolean CheckboxSignup() {
        if (!mSignupCheckbox.isChecked()) {
            Toast.makeText(this, "Please Agree to the Terms and Conditions", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    public void ConfirmValidation(View view) {

        try {
            if (!NameValidation() | !EmailValidation() | !PasswordValidation() | !CheckboxSignup()) {
                return;
            } else {
                if (PasswordValidation()) {
                    Boolean chkemail = db.chkemail(mSignupEmail.getEditText().getText().toString());
                    if (chkemail)
                    {
                        Boolean insert = db.insert(mSignupEmail.getEditText().getText().toString().trim(), mSignupPassword.getEditText().getText().toString());
                        if (insert == true) {
                            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            //String value="Hello world";
                            Intent i = new Intent(Sign_up.this, Login_Page.class);
                            i.putExtra("name",mSignupName.getEditText().getText().toString());
                            i.putExtra("emailAddress",mSignupEmail.getEditText().getText().toString());
                            startActivity(i);
                            //startActivity(new Intent(this, Login_Page.class));
                        }
                    } else
                        Toast.makeText(this, "Email Id Already Exists", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void clickedTerms(View view) {
        this.setContentView(R.layout.layout_terms_and_conditions);
    }
}
