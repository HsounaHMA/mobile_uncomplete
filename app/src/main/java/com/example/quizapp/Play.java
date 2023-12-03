package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Play extends AppCompatActivity {
    String[] question_list = {"What is the purpose of the static keyword in Java?",
            "What is the difference between ArrayList and LinkedList in Java?",
            "What is the purpose of the super keyword in Java?",
            "What is the difference between == and .equals() when comparing objects in Java?"};
    String[] choose_list = {"to make a variable or method belong to the class rather than an instance of the class", "To create a dynamic instance of a class", "To make a variable or method accessible only within its own class", "To define a constant variable",
            "arrayList is based on an array, while LinkedList is based on a doubly-linked list", "ArrayList is faster for random access, while LinkedList is better for frequent insertions and deletions", "ArrayList is a legacy class, and LinkedList is part of the Java Collections Framework", "ArrayList and LinkedList are interchangeable and can be used interchangeably in any situation",
            "to call the superclass constructor", "To indicate that a method can be overridden ", "To access the current instance of the class ", "To declare a variable with a constant value",
            "both are used interchangeably for object comparison", "== compares object references, while .equals() compares the content of objects", "== is used for primitive types, and .equals() is used for objects", "In Java, you use == to compare the contents of strings, and equals() for comparing integers and other primitive types"};
    String[] corect_list = {"to make a variable or method belong to the class rather than an instance of the class",
            "ArrayList is faster for random access, while LinkedList is better for frequent insertions and deletions",
            "to call the superclass constructor",
            "== compares object references, while .equals() compares the content of objects"};

    TextView q_num, text_question;
    Button choose_btn1, choose_btn2, choose_btn3, choose_btn4, next_btn;
    int milliseconds=1000 ,current_q = 0;
    boolean isclick = false;
    String valuechoose = "";
    Button btn_click;

    int score = 0 ;
    int final_declancher = 0 ;
    ProgressBar progress_bar ;
    int progress = 0  ;


    // timer declaration
    private CountDownTimer timer ;
    long time_left ;
    static final long total_time = 31000 ; // total time for each question .
    TextView timer_view  ;
    String time_left_txt ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


         //Timer initialisation
        timer_view = findViewById(R.id.Timer) ;
        time_left = total_time ;
        start_timer() ;


        q_num = findViewById(R.id.q_number);
        text_question = findViewById(R.id.question_);
        choose_btn1 = findViewById(R.id.chose_btn1);
        choose_btn2 = findViewById(R.id.chose_btn2);
        choose_btn3 = findViewById(R.id.chose_btn3);
        choose_btn4 = findViewById(R.id.chose_btn4);
        next_btn = findViewById(R.id.next_btn);


        progress_bar = findViewById(R.id.progressBar) ;

        q_num.setText("Question " + (current_q + 1) + "out of" + question_list.length + " questions");
        text_question.setText(question_list[current_q]);
        choose_btn1.setText(choose_list[current_q]);
        choose_btn2.setText(choose_list[current_q + 1]);
        choose_btn3.setText(choose_list[current_q + 2]);
        choose_btn4.setText(choose_list[current_q + 3]);



        // to pass to remplir data of the next question , and give indication if the answer is right wrong  .
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               if (isclick) {
                   timer.cancel();
                   time_left = 31000 ;
                    start_timer();
                    update_progressbar();
                    isclick = false;
                    final_declancher ++ ;


                    if (valuechoose.equals(corect_list[current_q])) {

                        score++ ;
                        Toast.makeText(Play.this, "correct", Toast.LENGTH_SHORT).show();
                        btn_click.setBackgroundResource(R.drawable.background_btn_corect);


                    } else {
                        Toast.makeText(Play.this, "wrong", Toast.LENGTH_SHORT).show();
                        btn_click.setBackgroundResource(R.drawable.background_btn_wrong);
                    }

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (current_q +1 < question_list.length ) {
                                current_q ++ ;
                                remplirdata();
                                valuechoose = "";
                                choose_btn1.setBackgroundResource(R.drawable.qs_background);
                                choose_btn2.setBackgroundResource(R.drawable.qs_background);
                                choose_btn3.setBackgroundResource(R.drawable.qs_background);
                                choose_btn4.setBackgroundResource(R.drawable.qs_background);
                            }
                        }
                    }, 1000 ) ;

                } else {
                   Toast.makeText(Play.this, "veuiller selectionner une reponse .", Toast.LENGTH_SHORT).show();
               }


                if (final_declancher == question_list.length){

                    timer.cancel();
                    Intent intent = new Intent(Play.this, Final_page.class);
                    intent.putExtra("final_score" , score) ;
                    startActivity(intent);
                }
            }
        });


    }

    public void start_timer() {


        timer_view.setText(time_left_txt);
                    // CountDownTimer() automatic function that takes 2 arguments ( time_left , tick_decrement .  )
        timer = new CountDownTimer( time_left , 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                time_left = millisUntilFinished ;
                updatetimer() ; // this is just to update the text view of the timer it has nothing to do with the numerical variables , they are automaticly updated throuth the start methode below .

            }

            // load next question or move to the next activity is the current question number is higher than the list lengh.
            @Override
            public void onFinish() {

                    if (current_q +1 < question_list.length) {
                        current_q = current_q + 1;
                        remplirdata();
                        valuechoose = "";
                        choose_btn1.setBackgroundResource(R.drawable.qs_background);
                        choose_btn2.setBackgroundResource(R.drawable.qs_background);
                        choose_btn3.setBackgroundResource(R.drawable.qs_background);
                        choose_btn4.setBackgroundResource(R.drawable.qs_background);
                        time_left = 31000 ;
                        start_timer();

                    }else {

                        Intent intent = new Intent(Play.this, Final_page.class);
                        intent.putExtra("final_score" , score) ;
                        startActivity(intent);

                    }

            }
        }.start() ;

    }

    public void updatetimer() {

        int minute = (int) (time_left / 1000 ) / 60 ;
        int seconds = (int) (time_left / 1000) % 60 ;
        time_left_txt = String.format( "%01d:%02d" , minute ,seconds ) ;
        timer_view.setText(time_left_txt);

    }

    // remplir le data du 2<= question
    void remplirdata() {

        q_num.setText("Question " + (current_q + 1) + "out of" + question_list.length + " questions");
        text_question.setText(question_list[current_q]);
        choose_btn1.setText(choose_list[4 * current_q]);
        choose_btn2.setText(choose_list[4 * current_q + 1]);
        choose_btn3.setText(choose_list[4 * current_q + 2]);
        choose_btn4.setText(choose_list[4 * current_q + 3]);


    }

    public void choose_click(View view) {


        btn_click = (Button) view;
        isclick = true;
        choose_btn1.setBackgroundResource(R.drawable.qs_background);
        choose_btn2.setBackgroundResource(R.drawable.qs_background);
        choose_btn3.setBackgroundResource(R.drawable.qs_background);
        choose_btn4.setBackgroundResource(R.drawable.qs_background);
        btn_click.setBackgroundResource(R.drawable.background_selected_choose);
        valuechoose = btn_click.getText().toString();

    }

    //function to increment the progress value in the progress bar  .
    void update_progressbar () {
        progress += 25 ;

        if (progress > 100 ) {
            progress = 100 ;
        }

        progress_bar.setProgress(progress);


    }


}