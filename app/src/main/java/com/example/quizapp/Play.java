package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    int current_q = 0;
    boolean isclick = false;
    String valuechoose = "";
    Button btn_click;

    TextView verifier;
    int score = 0 ;
    int final_declancher = 1 ;
    String final_score ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        q_num = findViewById(R.id.q_number);
        text_question = findViewById(R.id.question_);
        verifier = findViewById(R.id.verifier);

        choose_btn1 = findViewById(R.id.chose_btn1);
        choose_btn2 = findViewById(R.id.chose_btn2);
        choose_btn3 = findViewById(R.id.chose_btn3);
        choose_btn4 = findViewById(R.id.chose_btn4);
        next_btn = findViewById(R.id.next_btn);

        q_num.setText((current_q + 1) + "/" + question_list.length);
        text_question.setText(question_list[current_q - 0]);
        choose_btn1.setText(choose_list[current_q]);
        choose_btn2.setText(choose_list[current_q + 1]);
        choose_btn3.setText(choose_list[current_q + 2]);
        choose_btn4.setText(choose_list[current_q + 3]);

        // to pass to remplir data of the next question , and give indication if the answer is right wrong  .
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (isclick) {

                    verifier.setText(String.valueOf(score));
                    isclick = false;
                    final_declancher ++ ;

                   if (final_declancher == question_list.length+1){
                       
                       Intent intent = new Intent(Play.this, Final_score_activity.class);
                       startActivity(intent);
                       finish();
                   }

                    if (valuechoose.equals(corect_list[current_q])) {
                        score = score +1 ;
                        Toast.makeText(Play.this, "correct", Toast.LENGTH_SHORT).show();
                        btn_click.setBackgroundResource(R.drawable.background_btn_corect);


                    } else {
                        Toast.makeText(Play.this, "wrong", Toast.LENGTH_SHORT).show();
                        btn_click.setBackgroundResource(R.drawable.background_btn_wrong);
                    }
                    //this delays the execution of the code below for 2000 miliseconds .
                    new Handler().postDelayed(() -> {
                        if (current_q <= question_list.length) {
                            current_q = current_q + 1;
                            remplirdata();
                            valuechoose = "";
                            choose_btn1.setBackgroundResource(R.drawable.qs_background);
                            choose_btn2.setBackgroundResource(R.drawable.qs_background);
                            choose_btn3.setBackgroundResource(R.drawable.qs_background);
                            choose_btn4.setBackgroundResource(R.drawable.qs_background);
                        }
                    }, 2000);
                } else {
                   Toast.makeText(Play.this, "veuiller selectionner une reponse .", Toast.LENGTH_SHORT).show();
               }
            }
        });


    }


    // remplir le data du 2<= question
    void remplirdata() {

        q_num.setText((current_q + 1) + "/" + question_list.length);
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





    public String get_final_score(){
        return final_score ;
    }

}