package com.example.remasteredcalc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    double firstNum, secondNum;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Numbers
        Button num0 = findViewById(R.id.button_0);
        Button num1 = findViewById(R.id.button_1);
        Button num2 = findViewById(R.id.button_2);
        Button num3 = findViewById(R.id.button_3);
        Button num4 = findViewById(R.id.button_4);
        Button num5 = findViewById(R.id.button_5);
        Button num6 = findViewById(R.id.button_6);
        Button num7 = findViewById(R.id.button_7);
        Button num8 = findViewById(R.id.button_8);
        Button num9 = findViewById(R.id.button_9);

        //Symbols
        Button equals = findViewById(R.id.button_equals);
        Button klear = findViewById(R.id.button_ac);
        Button backspace = findViewById(R.id.button_c);
        Button sum = findViewById(R.id.button_plus);
        Button difference = findViewById(R.id.button_minus);
        Button product = findViewById(R.id.button_multiply);
        Button division = findViewById(R.id.button_divide);
        Button dot = findViewById(R.id.button_dot);
        Button mod = findViewById(R.id.button_mod);
        Button pow = findViewById(R.id.button_pow);


        TextView screen = findViewById(R.id.result_tv);

        klear.setOnClickListener(view -> {
            firstNum = 0;
            screen.setText("0");
        });

        //off.setOnClickListener(view -> screen.setVisibility(View.GONE));
        //on.setOnClickListener(view -> {
        //    screen.setVisibility(View.VISIBLE);
        //    screen.setText("0");
        //});

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        for (Button b : nums){
            b.setOnClickListener(view -> {
                if (!screen.getText().toString().equals("0")){
                    screen.setText(screen.getText().toString() + b.getText().toString());
                } else {
                    screen.setText(b.getText().toString());
                }
            });
        }

        ArrayList<Button> operations = new ArrayList<>();
        operations.add(equals);
        operations.add(klear);
        operations.add(backspace);
        operations.add(sum);
        operations.add(difference);
        operations.add(product);
        operations.add(division);
        operations.add(dot);
        operations.add(pow);
        operations.add(mod);

        for (Button b : operations) {
            b.setOnClickListener(view -> {
                firstNum = Double.parseDouble(screen.getText().toString());
                operation = b.getText().toString();
                screen.setText("0");
            });
        }

        backspace.setOnClickListener(view -> {
            String num = screen.getText().toString();
            if (num.length() > 1){
                screen.setText(num.substring(0, num.length()-1));
            }
            else if (num.length() == 1 && !num.equals("0")){
                screen.setText("0");
            }
        });

        dot.setOnClickListener(view -> {
            if (!screen.getText().toString().contains(".")){
                screen.setText(screen.getText().toString() + ".");
            }
        });

        equals.setOnClickListener(view -> {
            double secondNum = Double.parseDouble(screen.getText().toString());
            double result;
            switch(operation){
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "*":
                    result = firstNum * secondNum;
                    break;
                case "/":
                    result = firstNum / secondNum;
                    break;
                case "%":
                    result = firstNum % secondNum;
                    break;
                case "^":
                    result = Math.pow(firstNum, secondNum);
                    break;
                case "√":
                    result = Math.sqrt(secondNum);
                    break;
                case "AVG":
                    result = (firstNum + secondNum) / 2;
                    break;
                case "!":
                    int factorial_value = 1;
                    if (firstNum == 0){
                        result = factorial_value;
                    }else {
                        for (int i = 1; i <= firstNum; i++) {
                            factorial_value =  factorial_value * i;
                        }
                        result = factorial_value;
                    }
                    break;
                default:
                    result = firstNum + secondNum;
                    break;

            }
            screen.setText(String.valueOf(result));
            firstNum = result;
        });

    }
}