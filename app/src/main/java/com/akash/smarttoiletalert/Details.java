package com.akash.smarttoiletalert;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Details extends AppCompatActivity {
    private Button btn;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigation;
    private EditText name,age,phoneno,shift,gender;
    private Button add,emp;
    Member member;

    DatabaseReference databaseReference ;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name=findViewById(R.id.name_id);
        age=findViewById(R.id.age_id);
        gender=findViewById(R.id.gender_id);
        phoneno=findViewById(R.id.phoneno_id);
        shift=findViewById(R.id.time_id);
        add=findViewById(R.id.add_id);
        emp=findViewById(R.id.btn_emplyoee);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senddata();
            }
        });


        emp.setOnClickListener(v -> startActivity(new Intent(Details.this,employee.class )));




        drawerLayout = findViewById(R.id.details_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(Details.this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigation = (NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_HOME:
                        startActivity(new Intent(Details.this,Home.class));
                        return true;
                    case R.id.nav_logout:
                        startActivity(new Intent(Details.this, loginpage.class));
                        return true;
                    case R.id.nav_emplyoee:
                        startActivity(new Intent(Details.this, employee.class));
                        return true;

                    case R.id.nav_Details:
                       // startActivity(new Intent(Details.this, Details.class));
                        return true;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }

    private void senddata()
    {
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getInstance("https://smart-alert-toilet-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("ToiletManagementSystem");
        String nm,ag,ph,tm,gen;
        nm=name.getText().toString();
        ag=age.getText().toString();
        ph=phoneno.getText().toString();
        gen=gender.getText().toString();
        tm=shift.getText().toString();
        member=new Member(nm,ag,gen,ph,tm);



        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                databaseReference.child("EMPLYOEE").push().setValue(member);
//                check=snapshot.getValue(Member.class);
//                String value = snapshot.getValue(String.class);

                // after getting the value we are setting
                // our value to our text view in below line.
            name.setText("");
            age.setText("");
            gender.setText("");
            phoneno.setText("");
            shift.setText("");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(getApplicationContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
                name.setText("Fail to get data.");
            }
        });
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