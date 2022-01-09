package com.akash.smarttoiletalert;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginpage extends AppCompatActivity {
        private EditText user_name, pass_word;
        FirebaseAuth mAuth;
        DatabaseReference databaseReference ;
       FirebaseDatabase firebaseDatabase;
       public static String PREFS_NAME="MyPrefsFile";
    private ProgressDialog loadingbar;
    Member check;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.loginpage3);
            user_name=findViewById(R.id.email);
            pass_word=findViewById(R.id.password);
            Button btn_login = findViewById(R.id.btn_login);
            Button btn_sign = findViewById(R.id.btn_signup);
            firebaseDatabase=FirebaseDatabase.getInstance();
            mAuth=FirebaseAuth.getInstance();
         loadingbar=new ProgressDialog(this);


            btn_login.setOnClickListener(v -> {
                String email= user_name.getText().toString().trim();
                String password=pass_word.getText().toString().trim();
                if(email.isEmpty())
                {
                    user_name.setError("Email is empty");
                    user_name.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    user_name.setError("Enter the valid email");
                    user_name.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    pass_word.setError("Password is empty");
                    pass_word.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    pass_word.setError("Length of password is more than 6");
                    pass_word.requestFocus();
                    return;
                }
                loadingbar.setTitle("    Login your account  ");
                loadingbar.setMessage("please wait, while we are get into your account..");
                loadingbar.show();
                loadingbar.setCanceledOnTouchOutside(true);
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                    {
                        startActivity(new Intent(loginpage.this, Home.class));
                        loadingbar.dismiss();

                    }
                    else
                    {
                        Toast.makeText(loginpage.this,
                                "Please Check Your login Credentials",
                                Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                        btn_login.setError("login failed");
                        btn_login.requestFocus();

                    }

                });

                SharedPreferences sharedPreferences=getSharedPreferences(loginpage.PREFS_NAME,0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("hasloggedin",true);
                editor.commit();
            });
            btn_sign.setOnClickListener(v -> startActivity(new Intent(loginpage.this,registrationpage.class )));


        }




}


