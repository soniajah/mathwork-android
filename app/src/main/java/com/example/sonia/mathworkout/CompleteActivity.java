package com.example.sonia.mathworkout;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class CompleteActivity extends AppCompatActivity {
    long totaltime = 0;
    int wrongattempts = 0;
    public int answer = BraincruncherActivity.answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        final TextView complete = (TextView) findViewById(R.id.complete);
        complete.setText("COMPLETE");

        Intent intent = getIntent();
        totaltime = intent.getLongExtra("totaltime", -1);
        wrongattempts = intent.getIntExtra("wrongattempts", -1);

        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.complete);
        mp.start();



        new CountDownTimer(1000, 500) {

            public void onTick(long millisUntilFinished) {
//                complete.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
//                complete.setText("done!");
                Intent intent2 = new Intent(CompleteActivity.this,EndActivity.class);
                intent2.putExtra("totaltime", totaltime);
                intent2.putExtra("wrongattempts", wrongattempts);
                startActivity(intent2);
            }
        }.start();

    }

    @Override
    public void onBackPressed() {
    }

//    public void gonext(View view){
//        Intent intent2 = new Intent(this,EndActivity.class);
//        intent2.putExtra("totaltime", totaltime);
//        intent2.putExtra("wrongattempts", wrongattempts);
//        startActivity(intent2);
//    }
}
