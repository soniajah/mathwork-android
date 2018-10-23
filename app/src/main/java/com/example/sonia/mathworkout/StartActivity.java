package com.example.sonia.mathworkout;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {
    int i;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent intent = getIntent();
        final int nextActivity = intent.getIntExtra("nextActivity", -1);
        TextView textcategory = (TextView) findViewById(R.id.category);

        final String[] messages = {"3", "2", "1", "GO"};

        if (nextActivity == MainActivity.ADD_SUB_ACTIVITY) {
            textcategory.setText("Addition and Subtraction");
        }else if(nextActivity == MainActivity.MUL_DIV_ACTIVITY){
            textcategory.setText("Multiplication and Division");
//        }else if(nextActivity == MainActivity.BRAIN_ACTIVITY){
//            textcategory.setText("Brain Cruncher");
        }else if (nextActivity == MainActivity.BRAIN_ACTIVITY){
            level = PreBraincruncherActivity.Pressure_level;
            textcategory.setText("Pressure level: " + level);
        }

        i = 0;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (i >= 4) {
                    cancel();
                    return;
                }
                if (i < 3) {
                    try {
                        AssetFileDescriptor afd = getAssets().openFd("bip2.wav");
                        MediaPlayer player = new MediaPlayer();
                        player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        player.prepare();
                        player.start();
                    } catch (Exception exc){
                        exc.printStackTrace();
                        Log.w("soniaaudio", exc.getMessage() + "\n" + exc.getStackTrace());
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        TextView timerview = (TextView) findViewById(R.id.timer);
                        timerview.setText(messages[i]);
                        i++;
                        if (i >= 4) {
                            if (nextActivity == MainActivity.ADD_SUB_ACTIVITY) {
                                Intent intent1 = new Intent(StartActivity.this, addsubActivity.class);
                                startActivity(intent1);

                            }else if(nextActivity == MainActivity.MUL_DIV_ACTIVITY){
                                Intent intent1 = new Intent(StartActivity.this, muldivActivity.class);
                                startActivity(intent1);
                            }else if(nextActivity == MainActivity.BRAIN_ACTIVITY){
                                Intent intent1 = new Intent(StartActivity.this, BraincruncherActivity.class);
                                startActivity(intent1);
                            }
                        }
                    }
                });


            }
        }, 0, 1000);
    }

    @Override
    public void onBackPressed() {
    }
}
