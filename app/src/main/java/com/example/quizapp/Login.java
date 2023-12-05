package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {
    TextInputEditText email , password ;
    Button login_btn ;
    FirebaseAuth mAuth ;
    TextView textView ;

    //chcking if there is already a log in the app if so we intent to the main .
   /* @Override
   public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            Intent intent = new Intent(getApplicationContext() , MainActivity.class) ;
            startActivity(intent);
            finish();

        }
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email) ;
        password = findViewById(R.id.password) ;
        login_btn = findViewById(R.id.login_btn) ;
        mAuth = FirebaseAuth.getInstance() ;
        textView = findViewById(R.id.registernow) ;

        // on click listner to open Register page .
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intent : action that opens the page login
                Intent intent = new Intent(getApplicationContext() , Register.class) ;
                startActivity(intent);
                finish();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailtxt , passwortxt ;
                emailtxt = email.getText().toString() ;
                passwortxt = password.getText().toString() ;

                if (TextUtils.isEmpty(emailtxt)){
                    Toast.makeText(Login.this, "veuiller saisir votr email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwortxt)){
                    Toast.makeText(Login.this, "veuiller saisir un Mot de passe ", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(emailtxt, passwortxt)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Login Succesfully ", Toast.LENGTH_SHORT).show();
                                    // to pass to the mainActivity .
                                    Intent intent = new Intent(getApplicationContext() , MainActivity.class) ;
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });



            }
        });

    }
}