package com.example.sonia.mathworkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BraincruncherEnd2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_braincruncher_end2);

        Intent intent1 = getIntent();
        String answer = intent1.getStringExtra("answer");

        TextView textView = (TextView) findViewById(R.id.answertext);
        textView.setText(answer);
    }
    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void reviewAnswer(View view){
        // the answer explained by a list of actions

    }
    public void onBackPressed() {

    }
}
