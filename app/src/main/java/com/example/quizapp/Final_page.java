package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Final_page extends AppCompatActivity {

    TextView score ;
    Button play_again ;
    Button exit ;
    int score_finale ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page);
        score = findViewById(R.id.score) ;
        play_again = findViewById(R.id.play_again) ;
        exit = findViewById(R.id.exit) ;

        score_finale = getIntent().getIntExtra("final_score" , 1) ;
        score.setText(String.valueOf(score_finale));


        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Final_page.this , Play.class) ;
                startActivity(intent);

            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitbuttoncliked(v) ;
            }
        });

    }

    //function to quit the app
    public void exitbuttoncliked(View v) {
        finishAffinity();

    }
}