package com.example.ronitshrivastava.foodapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView name;
    TextView emailAddress;
    LinearLayout profile;
    String valueEmailAddress, valueName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.Homepage_Name);
        setSupportActionBar(toolbar);
        //profile = (LinearLayout) findViewById(R.id.profile);
        /*name = (TextView) findViewById(R.id.name);
        emailAddress = (TextView) findViewById(R.id.emailAddress);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //shared Preference








        //Bundle
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            valueEmailAddress = extras.getString("emailAddress");
            //emailAddress.setText(valueEmailAddress);
            //profile.addView(emailAddress);
            valueName = extras.getString("name");
            //name.setText(valueName);
            //profile.addView(name);
            //The key argument here must match that used in the other activity
            //Toast.makeText(this, ""+value, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            startActivity(new Intent(HomePage.this, SplashActivity.class));
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            SharedPreferences preferences = getSharedPreferences("MY_SHARED_PREF",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("emailAddress");
            editor.remove("name");
            editor.clear();
            editor.commit();
            Intent i = new Intent(HomePage.this,Login_Page.class);
            i.putExtra("isLoggedIn",false);
            startActivity(i);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            //mShareTextEditText=(EditText)findViewById(R.id.editText3);
            //String txt=mShareTextEditText.getText().toString();
            String txt="Hey! I am using this awesome app \"NutriSol\". It has some amazing features like" +
                    " BMI Calculator, Calories Counter and much more."; //Here will be the play store URL also.
            ShareCompat.IntentBuilder.from(this).setType("text/plain").setChooserTitle("Share this text with:").setText(txt).startChooser();


        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void clickedGet(View view) {

        startActivity(new Intent(HomePage.this, NutrientsActivity.class));
    }

    public void BMICheck(View view) {
        //Toast.makeText(this, ""+valueEmailAddress+valueName, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(HomePage.this, BMI_Activity.class));
    }


    public void clickedDiet(View view) {
        startActivity(new Intent(HomePage.this, DietActivity.class));
    }
}
