package com.example.sonia.mathworkout;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.RunnableFuture;
import android.os.Handler;
import java.util.logging.LogRecord;

import static android.R.attr.max;
import static android.R.id.progress;

public class BraincruncherActivity extends AppCompatActivity {
    public static int answer = 0;
    public List<Integer> list1 = new ArrayList<Integer>();
    public List<String> list2 = new ArrayList<>();
    public Integer[] prime_numbers = new Integer[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    SeekBar seekBar;
    CountDownTimer countDownTimer;
    TextView text;

    // onCreate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_braincruncher);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // initialization
        final Random r = new Random();
        answer = r.nextInt(10) + 1;
        text = (TextView) findViewById(R.id.seektext);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        String[] values = new String[] { "Add", "Sub", "Mul", "Div" };
        final ArrayList<String> operatorlist = new ArrayList<String>();
        for(int i = 0; i < values.length; ++i){
            operatorlist.add(values[i]);
        }

        // create a countdowntimer
        countDownTimer = new CountDownTimer(64000, 5000) {
            public void onTick(long millisUntilFinished) {

                // select the operator {+,-,*,/} operatorlist.get(myInt2)
                // select the number myInt1
                int myInt2 = r.nextInt(4);
                int myInt1;
                double sqrt = Math.sqrt(answer);
                int x = (int) sqrt;
                if (answer>60){
                    if( Math.pow(x,2)==Math.pow(sqrt,2)){
                        myInt2 = 3;
                    }else{
                        myInt2 = r.nextInt(2);
                    }
                }

                if (answer == 0) {
                    myInt2 = 0;
                    myInt1 = r.nextInt(21);
                } if (answer == 1) {
                    int k = r.nextInt(2);
                    myInt2 = 2*k;
                    myInt1 = r.nextInt(21);
                }else if(operatorlist.get(myInt2)== "Div"){
                    if(Arrays.asList(prime_numbers).contains(answer) && answer!=2){
                        //answer-1 or answer/answer
//                        myInt2 = 1;
//                        myInt1 = 1;
                        //answer-1 or answer/answer
                        myInt1 = answer;
                    }else if( Math.pow(x,2)==Math.pow(sqrt,2) && answer>60){
                        myInt1 = x;
                        myInt2 = 3;
                    }else if(answer % 2 == 0){
                        int piliko = answer/2;
                        int k = r.nextInt(2);
                        myInt1 = piliko*k + 2 * (1-k);
                    }else if(answer % 3 == 0){
                        myInt1 = 3;
                    }else if(answer % 5 == 0){
                        myInt1 = 5;
                    }else if(answer % 7 == 0){
                        myInt1 = 7;
                    }else{
                        myInt1 = r.nextInt(answer+1);
                    }
                }else if(operatorlist.get(myInt2)== "Mul"){
                    if (answer>=50){
                        myInt2 = r.nextInt(2);
                        myInt1 = r.nextInt(10);
                    }else if (answer<50 && answer>10) {
                        myInt1 = r.nextInt(5);
                    }else{
                        myInt1 = r.nextInt(12);
                    }
                }else if (operatorlist.get(myInt2)== "Sub"){
                    myInt1 = r.nextInt(answer);
                }else {
                    if (answer<=50){
                        myInt1 = r.nextInt(31);
                    } else {
                        myInt1 = r.nextInt(7);
                    }
                }

                list1.add(myInt1);
                list2.add(operatorlist.get(myInt2));

                // calculate the answer
                if (operatorlist.get(myInt2) == "Add") {
                    answer += myInt1;
                }else if (operatorlist.get(myInt2) == "Sub") {
                    answer -= myInt1;
                }else if (operatorlist.get(myInt2) == "Mul") {
                    answer *= myInt1;
                }else {
                    answer /= myInt1;
                }

                // Screen Textview
                if (millisUntilFinished >= 60000){
                    text.setText("Start with " + String.valueOf(answer));
                } else {
                    text.setText(String.valueOf(operatorlist.get(myInt2)) + " " + String.valueOf(myInt1));
//                    text3.setText(" seconds remaining: " + millisUntilFinished / 1000);
                }

                // animate the seekbar to slide with timer
                seekBar.setMax(0);
                seekBar.setMax(max);
                if(android.os.Build.VERSION.SDK_INT >= 11){
                    // will update the "progress" propriety of seekbar until it reaches progress
                    ObjectAnimator animation = ObjectAnimator.ofInt(seekBar, "progress", progress);
                    animation.setDuration(5000); // 1 second
                    animation.setInterpolator(new DecelerateInterpolator());
                    animation.start();
                }
                else
                    seekBar.setProgress(progress); // no animation on Gingerbread or lower

                seekBar.refreshDrawableState();
            }
            // action after timer stops
            public void onFinish() {
                Intent intent2 = new Intent(BraincruncherActivity.this,BraincruncherEnd.class);
//                intent2.putExtra("answer", answer);
                startActivity(intent2);
            }
        };

        // run the timer
        countDownTimer.start();
    }

//    other functions
    @Override
    public void onBackPressed() {
        Intent intent3 = new Intent(this, PreBraincruncherActivity.class);
        startActivity(intent3);
        countDownTimer.cancel();
    }
}
