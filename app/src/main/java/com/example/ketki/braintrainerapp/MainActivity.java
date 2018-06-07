package com.example.ketki.braintrainerapp;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView pointsTextView;
    TextView resultTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");

        generateQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score : " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                resultTextView.setTextColor(Color.BLUE);
            }
        }.start();
    }

    public void generateQuestion(){
        Random random = new Random();

        int a = random.nextInt(26);
        int b = random.nextInt(26);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        int incorrectAnswer;

        answers.clear();

        for(int i=0; i<4; i++){
            if(i == locationOfCorrectAnswer)
                answers.add(a + b);
            else{
                incorrectAnswer = random.nextInt(51);

                while(incorrectAnswer == a + b)
                    incorrectAnswer = random.nextInt(51);

                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setTextColor(Color.GREEN);
        }
        else{
            resultTextView.setText("Wrong!");
            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setTextColor(Color.RED);
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
    }
}
