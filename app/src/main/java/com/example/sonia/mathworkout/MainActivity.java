package com.example.sonia.mathworkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static int ADD_SUB_ACTIVITY = 1;
    public static int MUL_DIV_ACTIVITY = 2;
    public static int BRAIN_ACTIVITY = 3;

//    public static int current_selection = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void additionSubtraction(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        intent.putExtra("nextActivity", ADD_SUB_ACTIVITY);
//        current_selection = ADD_SUB_ACTIVITY;
        startActivity(intent);
    }

    public void multiplicationDivision(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        intent.putExtra("nextActivity", MUL_DIV_ACTIVITY);
//        current_selection = MUL_DIV_ACTIVITY;
        startActivity(intent);
    }

    public void brainCruncher(View view) {
        Intent intent = new Intent(this, PreBraincruncherActivity.class);
//        intent.putExtra("nextActivity", BRAIN_ACTIVITY);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }

}
