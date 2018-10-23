package com.example.sonia.mathworkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class PreBraincruncherActivity extends AppCompatActivity {
    public static String Pressure_level = "low";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_braincruncher);

        TextView text1 = (TextView) findViewById(R.id.prebraincrun);
        text1.setText(" The Brain Cruncher");

        TextView text2 = (TextView) findViewById(R.id.improve);
        text2.setText("Improve your mental arithmetic under pressure ");

    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.low_pressure:
                if (checked)
                    Pressure_level = "low";
                break;
            case R.id.medium_pressure:
                if (checked)
                    Pressure_level = "medium";
                break;
            case R.id.high_pressure:
                if(checked)
                    Pressure_level = "high";
                break;
        }

    }

    // get the selected dropdown list value
    public void startButton(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        intent.putExtra("nextActivity", MainActivity.BRAIN_ACTIVITY);
        intent.putExtra("level", Pressure_level);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent3 = new Intent(this, MainActivity.class);
        startActivity(intent3);
    }
}
