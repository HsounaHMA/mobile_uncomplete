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

public class Register extends AppCompatActivity {

    TextInputEditText email , password ;
    Button register_btn ;
    FirebaseAuth mAuth ;
    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        email = findViewById(R.id.email) ;
        password = findViewById(R.id.password) ;
        register_btn = findViewById(R.id.register_btn) ;
        mAuth = FirebaseAuth.getInstance() ;
        textView = findViewById(R.id.loginnow) ;

        // on click listner to open login page .
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intent : action that opens the page login
                Intent intent = new Intent(getApplicationContext() , Login.class) ;
                startActivity(intent);
                finish();
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailtxt , passwortxt ;
                emailtxt = email.getText().toString() ;
                passwortxt = password.getText().toString() ;

                if (TextUtils.isEmpty(emailtxt)){
                    Toast.makeText(Register.this, "veuiller saisir un email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwortxt)){
                    Toast.makeText(Register.this, "veuiller saisir un Mot de passe ", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(emailtxt, passwortxt)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                                    //vers Login .
                                    Intent intent = new Intent(getApplicationContext() , Login.class) ;
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Register.this, "Creation Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }

        });
    }
}