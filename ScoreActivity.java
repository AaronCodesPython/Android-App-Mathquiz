package com.example.mathquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    public void onPlayAgain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Bundle extras = getIntent().getExtras();
        int count = extras.getInt("count");
        int countRight = extras.getInt("countRight");

        Log.i("DevInfo", String.valueOf(count));
        Log.i("DevInfo", String.valueOf(countRight));

        TextView textView = (TextView) findViewById(R.id.textView3);
        String message = String.format("You reached %s points! \n You had %s/%s questions right!",countRight,countRight, count);
        textView.setText(message);

        Button PlayAgain = (Button) findViewById(R.id.button7);
        PlayAgain.setText("Play again");

    }
}