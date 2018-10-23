package com.example.sonia.mathworkout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class feelingcleverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelingclever);

        List<String> mylistStr = new ArrayList<>(Arrays.asList("addition", "subtraction", "multiplication", "division"));
        Random rand3 = new Random();
//        int index = rand3.nextInt(mylistStr.size());
        int index = 3;
        final int result;

        Random rand1 = new Random();
        final int number1 = rand1.nextInt(10); // Gives n such that 0 <= n < 10

        Random rand2 = new Random();
        final int number2 = rand2.nextInt(10); // Gives n such that 0 <= n < 10


        if (mylistStr.get(index) == "addition"){
            result = number1 + number2;
        }
        else if (mylistStr.get(index) == "subtraction"){
            result = number1 - number2;
        }
        else if (mylistStr.get(index) == "multiplication"){
            result = number1 * number2;
        }
        else if (mylistStr.get(index) == "division"){
            if (number2 == 0){
                Random rand4 = new Random();
                int number3 = rand4.nextInt(number1);
                result = number1 / number3;
                System.out.println(number3);
            }else{ result = number1 / number2;}
        }
        else{result = 0;}

        System.out.println("SONIAexample");
        System.out.println(index);
        System.out.println(mylistStr.get(index));


        System.out.println(number1);
        System.out.println(number2);
        System.out.println(result);



//        Iterator<String> iterator = mylistStr.iterator();
//        while(iterator.hasNext()){
//            String obj = iterator.next();
    }
}
