package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Final_score_activity extends AppCompatActivity {

    TextView score ;
    Button play_again ;
    Button exit ;
    String final_score ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_finale);
         score = findViewById(R.id.score) ;
         play_again = findViewById(R.id.play_again) ;
         exit = findViewById(R.id.exit) ;

         Play play = new Play() ;
         this.final_score = play.get_final_score() ;
         score.setText(this.final_score) ;



    }
}