package com.example.sonia.mathworkout;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BraincruncherEnd extends AppCompatActivity {
    public int answer = BraincruncherActivity.answer;
    int ti = 0;
    int counter = 0;
//    MediaPlayer player = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_braincruncher_end);

        TextView textView = (TextView) findViewById(R.id.OperationField);
        textView.setText("Final Answer: " + String.valueOf(answer));
    }


    public void buttonClick(View v) throws IOException {
        TextView textView = (TextView) findViewById(R.id.OperationField);
        TextView helpview = (TextView) findViewById(R.id.helpview);
        Button button = (Button) v;
        String buttontext = button.getText().toString();
        int buttontextInt = Integer.parseInt(buttontext);
        ti = ti * 10 + buttontextInt;
        helpview.setText(String.valueOf(ti));

        if(String.valueOf(ti).length() == String.valueOf(answer).length()) {
            //
            if (ti == answer) {
                AssetFileDescriptor afd = getAssets().openFd("positive.wav");
                MediaPlayer player = new MediaPlayer();
                player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                player.prepare();
                player.start();
                ti = 0;
                Intent intent = new Intent(this, BraincruncherEnd2.class);
                intent.putExtra("answer", "Bravo");
                startActivity(intent);
            } else {
                counter++;
                if (counter > 2) {
                    Intent intent = new Intent(this, BraincruncherEnd2.class);
                    intent.putExtra("answer", "Wrong Answer");
                    startActivity(intent);
                } else {
                    AssetFileDescriptor afd = getAssets().openFd("negative.wav");
                    MediaPlayer player = new MediaPlayer();
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    player.prepare();
                    player.start();
                    ti = 0;
                    helpview.setText("");
                }
            }
        } else {
            helpview.setText(String.valueOf(ti));
        }
    }

    public void clearButton(View view){
        ti=0;
        TextView helpview = (TextView) findViewById(R.id.helpview);
        helpview.setText(" ");
    }

    public void onBackPressed() {
        Intent intent = new Intent(BraincruncherEnd.this, PreBraincruncherActivity.class);
        startActivity(intent);
    }
}
