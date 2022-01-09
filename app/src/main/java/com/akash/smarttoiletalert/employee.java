package com.akash.smarttoiletalert;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class employee extends AppCompatActivity {
    private RecyclerView recyclerView2;
    List<Member>listItem=new ArrayList<>();
    List<Member>result=new ArrayList<>();
    DatabaseReference databaseReference,databaseReference1,databaseReference2;
    FirebaseDatabase firebaseDatabase;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        getdet();




        drawerLayout = findViewById(R.id.emplyoee_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(employee.this, drawerLayout, R.string.nav_open, R.string.nav_close);

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
                        startActivity(new Intent(employee.this,Home.class));
                        return true;
                    case R.id.nav_logout:
                        startActivity(new Intent(employee.this, loginpage.class));
                        return true;

                    case R.id.nav_Details:
                         startActivity(new Intent(employee.this, Details.class));
                        return true;
                    case  R.id.nav_emplyoee:
                        return true;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }

    private void getdet() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getInstance("https://smart-alert-toilet-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("ToiletManagementSystem");
        databaseReference1 = databaseReference.child("EMPLYOEE");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                // Iterable<DataSnapshot> children=snapshot.getChildren();
                List<Member>us=new ArrayList<>();
                // Result will be holded Here


                for (DataSnapshot item :snapshot.getChildren())
                {

                    Member t = item.getValue(Member.class);
                    us.add(t);
                }

                //Toilet toilet2 = snapshot.getValue(Toilet.class);
                recyclerView2 = findViewById(R.id.recycleview2);
                RecycleEmpl detada= new RecycleEmpl(employee.this);
                detada.setdet(us);
                recyclerView2.setAdapter(detada);

                recyclerView2.setLayoutManager(new LinearLayoutManager(employee.this));

                //String value = snapshot.getValue(String.class);

                // after getting the value we are setting
                // our value to our text view in below line.


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(getApplicationContext(), "Fail' to get data.", Toast.LENGTH_SHORT).show();


            }
        });
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

