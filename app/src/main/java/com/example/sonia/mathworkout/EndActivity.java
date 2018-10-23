package com.example.sonia.mathworkout;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Intent intent = getIntent();
        long totaltime = intent.getLongExtra("totaltime", -1);
        int wrongatt = intent.getIntExtra("wrongattempts", -1);

        int seconds = (int) (totaltime / 1000) % 60 ;
        int minutes = (int) ((totaltime / (1000*60)) % 60);
        int hours   = (int) ((totaltime / (1000*60*60)) % 24);
        String timing = String.valueOf(hours)+ ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds);

        long timepenalty = (totaltime/1000) + (wrongatt * 5);
        int seconds2 = (int) (timepenalty  % 60);
        int minutes2 = (int) ((timepenalty / 60) % 60);
        int hours2   = (int) ((timepenalty / (60*60)) % 24);
        String timeafterpenalty = String.valueOf(hours2)+ ":" + String.valueOf(minutes2) + ":" + String.valueOf(seconds2);

        TextView wrongattempt = (TextView) findViewById(R.id.wrongattempts);
        TextView time_penalty = (TextView) findViewById(R.id.timepenalty);
        TextView yourtime = (TextView) findViewById(R.id.yourtime);
        TextView lastview = (TextView) findViewById(R.id.lastview);

        wrongattempt.setText("Wrong Attempts : " + String.valueOf(wrongatt));
        time_penalty.setText("Time Penalty : " + String.valueOf(wrongatt) + " x " + "5 sec");
        yourtime.setText("Your Time : " + timing );
        lastview.setText("Final Time : " + timeafterpenalty );


    }
    public void onBackPressed() {
        Intent intent = new Intent(EndActivity.this, MainActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
    public void home(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void submitscore(View view){
        //Django
    }
}
