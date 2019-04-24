package com.example.roadmaster;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LoginActicity extends AppCompatActivity {
    private EditText email_field, password_field;
    private String email, password;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acticity);
        email_field = findViewById(R.id.email_field);
        password_field = findViewById(R.id.password_field);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void login(View v){
        email = email_field.getText().toString().trim();
        password = password_field.getText().toString().trim();
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill in the required information", Toast.LENGTH_SHORT).show();
        }
        else{
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                user = firebaseAuth.getCurrentUser();
                                startActivity(new Intent(LoginActicity.this, MapsActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(LoginActicity.this, "There was an error\nPlease try again later", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
            );
        }
    }
}
