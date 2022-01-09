package com.akash.smarttoiletalert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference ;
    FirebaseDatabase firebaseDatabase;
    Member check;
    private TextView name;
    private static int SPLASH_TIME_OUT=3000;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private ProgressDialog loadingbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingbar=new ProgressDialog(this);
        loadingbar.setTitle("    Loading Your HomePage  ");
        loadingbar.setMessage("please wait, while we are get into your account..");
        loadingbar.show();
        loadingbar.setCanceledOnTouchOutside(true);

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getInstance("https://smart-alert-toilet-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("RAMANI");
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            SharedPreferences sharedPreferences=getSharedPreferences(loginpage.PREFS_NAME,0);
            boolean hasloggedin=sharedPreferences.getBoolean("hasloggedin",false);
            if(hasloggedin){
                startActivity(new Intent(MainActivity.this, Home.class));
                finish();
                loadingbar.dismiss();
            }
            else
            {
                startActivity(new Intent(MainActivity.this,loginpage.class));
                finish();
                loadingbar.dismiss();
            }
        }
    },SPLASH_TIME_OUT);
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}