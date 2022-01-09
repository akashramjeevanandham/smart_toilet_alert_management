package com.akash.smarttoiletalert;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Home extends AppCompatActivity {
    private Button btn;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigation;
    private ProgressDialog loadingbar;
    DatabaseReference databaseReference1 ,databaseReference2,databaseReference3;
    FirebaseDatabase firebaseDatabase1,firebaseDatabase2;
    private RecyclerView recyclerView;
    List<Toilet>additem=new ArrayList<>();
    Toilet userlist;
    Toilet re;
    String tname="Toilet";
    Toilet toilets,addtoilet;
    Calendar calendar = Calendar.getInstance();


    @TargetApi(Build.VERSION_CODES.O)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadingbar=new ProgressDialog(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        firebaseDatabase1 = FirebaseDatabase.getInstance();
        databaseReference1 = firebaseDatabase1.getInstance("https://smart-alert-toilet-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("ToiletManagementSystem");

        databaseReference2=databaseReference1.child("IOT/TOILET1");
        databaseReference3=databaseReference1.child("Toilet");

        gettoilet();
//        databaseReference2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull  DataSnapshot snapshot)
//            {
//
//                // Result will be holded Here
//
//                for (DataSnapshot item : snapshot.getChildren())
//                {
//                    String m1=snapshot.child("toiletnm").getValue(String.class).toString();
//                    String m2=snapshot.child("time").getValue(String.class).toString();
//                    String m3=snapshot.child("irvalue").getValue(String.class).toString();
//                    String m4=snapshot.child("methanevalue").getValue(String.class).toString();
//                    re= new Toilet(m1,m2,m3,m4);
//                }
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        databaseReference3.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot)
//            {
//
//                   String n1=re.getToiletnm();
//                    String n2=re.getTime();
//                   String n3=re.getIrvalue();
//                   String n4=re.getMethanevalue();
//
//
//
//
//                    for (DataSnapshot item :snapshot.getChildren()) {
//                        Toilet t = item.getValue(Toilet.class);
//                        if(t.getToiletnm()==n1)
//                        {
//
//                            databaseReference1.child(item.getKey()).setValue(re);
//                        }
//
//                    }
//
//                }
//
//
//            @Override
//            public void onCancelled(@NonNull  DatabaseError error) {
//
//            }
//        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int c = Toilet.count;
                c++;
                //addtoilet = new Toilet(tname + c);
                String M = tname + c;



                LayoutInflater li = LayoutInflater.from(Home.this);
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Home.this);

                alertDialogBuilder.setView(promptsView);

                 EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
//                                        result.setText(userInput.getText());

                                        //additem.add(t);


                                        databaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                // this method is call to get the realtime
                                                // updates in the data.
                                                // this method is called when the data is
                                                // changed in our Firebase console.
                                                // below line is for getting the data from
                                                // snapshot of our database.
                                                String check=userInput.getText().toString();

                                                Toilet s = new Toilet(check,"0","0","0","0");
//                                                List<Toilet> userlist = new ArrayList<>();
//                                                // Result will be holded Here
//                                                for (DataSnapshot item : snapshot.getChildren()) {
//                                                    Toilet t = item.getValue(Toilet.class);
//                                                    userlist.add(t);
//                                                }


                                                    //String i = t.getToiletnm().toString();
//                                                    String j = Integer.parseInt(currentDateandTime);
//                                                    if (i == j) {
//                                                        phoneNo = t.getPhoneno();
//                                                        NM=t.getName();

                                               databaseReference3.child(check).setValue(s);

                                                //String value = snapshot.getValue(String.class);
                                                // after getting the value we are setting
                                                // our value to our text view in below line.


                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                // calling on cancelled method when we receive
                                                // any error or we are not able to get the data.
                                                Toast.makeText(getApplicationContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();


                                            }
                                        });

//
                                    }
                                })

                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });


                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });


                SimpleDateFormat sdf = new SimpleDateFormat("hh");
                String currentDateandTime = sdf.format(new Date());




                drawerLayout = findViewById(R.id.Home_drawer_layout);
                actionBarDrawerToggle = new ActionBarDrawerToggle(Home.this, drawerLayout, R.string.nav_open, R.string.nav_close);

                // pass the Open and Close toggle for the drawer layout listener
                // to toggle the button
                drawerLayout.addDrawerListener(actionBarDrawerToggle);
                actionBarDrawerToggle.syncState();

                // to make the Navigation drawer icon always appear on the action bar
                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

                navigation = findViewById(R.id.navigation_view);
                navigation.setNavigationItemSelectedListener(menuItem -> {
                    int id = menuItem.getItemId();
                    switch (id) {
                        case R.id.nav_HOME:
                            startActivity(new Intent(Home.this, Home.class));
                            finish();
                            return true;
                        case R.id.nav_logout:
                            startActivity(new Intent(Home.this, loginpage.class));
                            finish();
                            return true;
                        case R.id.nav_emplyoee:
                            startActivity(new Intent(Home.this, employee.class));
                            return true;

                        case R.id.nav_Details:
                            startActivity(new Intent(Home.this, Details.class));
                            finish();
                            return true;

                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                });

            }



                                   private void gettoilet() {



                                       databaseReference3.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                                               // this method is call to get the realtime
                                               // updates in the data.
                                               // this method is called when the data is
                                               // changed in our Firebase console.
                                               // below line is for getting the data from
                                               // snapshot of our database.
                                              // Iterable<DataSnapshot> children=snapshot.getChildren();
                                               List<Toilet>us=new ArrayList<>();
                                               // Result will be holded Here

                                               SimpleDateFormat mdformat = new SimpleDateFormat("HH");
                                               String strDate = mdformat.format(calendar.getTime()).toString();
                                              databaseReference3.child("TOILET1/time").setValue(strDate);
                                               for (DataSnapshot item :snapshot.getChildren())
                                               {

                                                   Toilet t = item.getValue(Toilet.class);
                                                   us.add(t);
                                               }

                                               //Toilet toilet2 = snapshot.getValue(Toilet.class);
                                               recyclerView = findViewById(R.id.recycleview);
                                               RAdapter radapter = new RAdapter(Home.this);
                                               radapter.setToilet(us);
                                               recyclerView.setAdapter(radapter);

                                               recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));

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







