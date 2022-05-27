package com.example.mathquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    long TimerLength = 30000;
    long TimerCountDown = TimerLength;
    double Solution;
    int count=0;
    int countRight=0;
    Button CountButton;
    TextView textView;
    Button[] buttonArray;

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public double[] setNewRandomEquation(TextView textView){
        double start = 1;
        double end = 420;



        double random1 = new Random().nextDouble();
        double random2 = new Random().nextDouble();
        double result1 = start + (random1 * (end - start));
        double result2 = start + (random2 * (end - start));

        result1= round(result1,1);
        result2= round(result2,1);

        textView.setText(Double.toString(result1)+" + "+Double.toString(result2));

        double[] arr = {result1,result2};
        return arr;
    }

    public void setRandomAnswers(double Result, Button[] buttons){
        Button button1 = buttons[0];
        Button button2 = buttons[1];
        Button button3 = buttons[2];
        Button button4 = buttons[3];

        double start = 1;
        double end = 420;

        DecimalFormat nf = new DecimalFormat("#");
        Random rd = new Random();
        double[] array = new double[3];
        for (int i = 0; i < array.length; i++) {

            double RandomNumber = rd.nextDouble();
            double RandomResult = start + (RandomNumber * (end - start));
            //RandomResult = Double.parseDouble(df.format(RandomResult));

            if(RandomResult==Result){
                i--;
                continue;
            }
            else{
                array[i] =round(RandomResult,1);
            }


        }
        double ButtonStart = 1;
        double ButtonEnd = 4;
        double RandomButton = rd.nextDouble();
        double RandomButtonResult = ButtonStart + (RandomButton * (ButtonEnd - ButtonStart));
        RandomButtonResult = Integer.parseInt(nf.format(RandomButtonResult));
        //TODO: Den 4 buttons zufällig die 4 ergebnisse zuweißen, die nicht die Lösung sind und einem Button die Lösung zuweisen
        Log.i("DevInfo", "RandomButtonResult "+String.valueOf(RandomButtonResult));
        if(RandomButtonResult==1){
            button1.setText(Double.toString(Result));
            button2.setText(Double.toString(array[0]));
            button3.setText(Double.toString(array[1]));
            button4.setText(Double.toString(array[2]));


        }
        else if(RandomButtonResult==2){
            button2.setText(Double.toString(Result));
            button1.setText(Double.toString(array[0]));
            button3.setText(Double.toString(array[1]));
            button4.setText(Double.toString(array[2]));

        }
        else if(RandomButtonResult==3){
            button3.setText(Double.toString(Result));
            button2.setText(Double.toString(array[0]));
            button1.setText(Double.toString(array[1]));
            button4.setText(Double.toString(array[2]));

        }
        else if(RandomButtonResult==4){
            button4.setText(Double.toString(Result));
            button2.setText(Double.toString(array[0]));
            button3.setText(Double.toString(array[1]));
            button1.setText(Double.toString(array[2]));


        }

    }

    public void onClick(View view){
        Button clickedButton = (Button) view;
        String ButtonText = clickedButton.getText().toString();
        double check1= Double.parseDouble(ButtonText);

        if(check1==Solution){
            Log.i("DevInfo", "RICHTIG");
            count+=1;
            countRight+=1;

            String NewCountButtonText = String.format("%s/%s",Integer.toString(countRight),Integer.toString(count) );
            CountButton.setText(NewCountButtonText);

            double[] dArray =setNewRandomEquation(textView);
            Solution = dArray[0]+dArray[1];
            setRandomAnswers(round(Solution,1),buttonArray);


        }
        else{
            Log.i("DevInfo", "FALSCH!");
            count+=1;
            String NewCountButtonText = String.format("%s/%s",Integer.toString(countRight),Integer.toString(count) );
            CountButton.setText(NewCountButtonText);

            double[] dArray =setNewRandomEquation(textView);
            Solution = dArray[0]+dArray[1];
            setRandomAnswers(round(Solution,1),buttonArray);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, ScoreActivity.class);

        //intent.putExtra("countRight", String.valueOf(countRight));

        textView = (TextView) findViewById(R.id.textView);

        CountButton = (Button) findViewById(R.id.button2);
        Button button1 =(Button) findViewById(R.id.button4);
        Button button2 =(Button) findViewById(R.id.button6);
        Button button3 =(Button) findViewById(R.id.button3);
        Button button4 =(Button) findViewById(R.id.button5);


        buttonArray = new Button[]{button1, button2, button3, button4};

        double[] dArray =setNewRandomEquation(textView);
        Solution = dArray[0]+dArray[1];
        Log.i("DevInfo", String.valueOf(Solution));

        setRandomAnswers(round(Solution,1),buttonArray);

        Button TimerButton = (Button) findViewById(R.id.button);
        CountDownTimer timer = new CountDownTimer(TimerLength,1000) {
            @Override
            public void onTick(long l) {
                long sec = (TimerCountDown / 1000) % 60;

                TimerButton.setText(String.format("%s s", sec));
                TimerCountDown=TimerCountDown-1000;
            }

            @Override
            public void onFinish() {
                intent.putExtra("count",count);
                intent.putExtra("countRight",countRight);
                startActivity(intent);

                Log.i("DevInfo", "Finish");

            }
        }.start();

    }



}