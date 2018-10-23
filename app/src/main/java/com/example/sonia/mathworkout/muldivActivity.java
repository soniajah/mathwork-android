package com.example.sonia.mathworkout;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class muldivActivity extends AppCompatActivity {
    public static long startTime;
    public static long endTime;
    public static long totalTime;
    int counter = 0;
    int ti = 0;
    int result;
    int number1;
    int number2;
    int wrong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsub);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        startTime = System.currentTimeMillis();
        String toprint = initiateNumbers();
        TextView textView = (TextView) findViewById(R.id.OperationField);
        textView.setText(toprint);
    }

    public String initiateNumbers(){
        Random rand1 = new Random();
        Random rand2 = new Random();
        Random rand3 = new Random();
        List<String> operators = new ArrayList<>(Arrays.asList("*", "/"));
        int index = rand3.nextInt(operators.size());
        if (operators.get(index) == "*"){
            number1 = rand1.nextInt(9)+ 1; // Gives n such that 0 <= n < 10
            number2 = rand2.nextInt(number1); // Gives n such that 0 <= n < number1
            result = number1 * number2;
        }
        else{
            number2 = rand1.nextInt(9) + 1; // Gives n such that 0 <= n < 10
            number1 = rand2.nextInt(10)*number2; // Gives n such that 0 <= n < number2
            result = number1 / number2;
        }
        String toprint = String.valueOf(number1) + operators.get(index) + String.valueOf(number2) + "=" + String.valueOf(result);
        return toprint;
    }

    public void buttonClick(View v) throws IOException {
        TextView textView = (TextView) findViewById(R.id.OperationField);
        TextView helpview = (TextView) findViewById(R.id.helpview);
        Button button = (Button) v;
        String buttontext = button.getText().toString();
        int buttontextInt = Integer.parseInt(buttontext);
        ti = ti * 10 + buttontextInt;
        helpview.setText(String.valueOf(ti));

        if(String.valueOf(ti).length() == String.valueOf(result).length()){
            if(ti == result){
                counter++;
                if (counter>=5){
                    endTime = System.currentTimeMillis();
                    totalTime = endTime - startTime;
                    Intent intent = new Intent(this,CompleteActivity.class);
                    intent.putExtra("totaltime", totalTime);
                    intent.putExtra("wrongattempts", wrong);
                    startActivity(intent);
                }else{
                    AssetFileDescriptor afd = getAssets().openFd("positive.wav");
                    MediaPlayer player = new MediaPlayer();
                    player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    player.prepare();
                    player.start();
                    String newprint = initiateNumbers();
                    textView.setText(newprint);
                    ti = 0;
                    helpview.setText("");
                }
            }else{
                wrong++;
                AssetFileDescriptor afd = getAssets().openFd("negative.wav");
                MediaPlayer player = new MediaPlayer();
                player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                player.prepare();
                player.start();
                ti = 0;
                helpview.setText("");
            }
        }else{
            helpview.setText(String.valueOf(ti));
        }
    }

    public void clearButton(View view){
        ti=0;
        TextView helpview = (TextView) findViewById(R.id.helpview);
        helpview.setText(" ");
    }

    public void onBackPressed() {
        Intent intent = new Intent(muldivActivity.this, MainActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}