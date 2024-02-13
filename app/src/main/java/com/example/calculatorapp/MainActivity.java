package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView expressionView;
    TextView resultView;
    int num1=0;
    int num2=0;
    String num2str = "";
    boolean writingToNum2 = false;
    String operator = "";
    boolean endOfCalc = false;
    int numberOfOperators = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressionView = findViewById(R.id.expressionView);
        resultView = findViewById(R.id.resultView);
    }


    public void funcNumbers(View view) {
        if(endOfCalc)
            clearCalc(view);

        Button button = (Button) view;
        expressionView.append(button.getText().toString());

        if(writingToNum2){
            num2str += button.getText().toString();
        }
    }

    public void funcOperators(View view) {
        if(endOfCalc)
            clearCalc(view);

        if(expressionView.getText().toString().equals("")) {
            resultView.setText("Must enter a number first");
            endOfCalc = true;
            return;
        }

        if(numberOfOperators == 1){
            resultView.setText("You can only use one operator.");
            endOfCalc = true;
            numberOfOperators = 0;
            return;
        }

        numberOfOperators++;
        num1 = Integer.parseInt(expressionView.getText().toString());

        Button button = (Button) view;
        expressionView.append(button.getText().toString());
        operator = button.getText().toString();

        writingToNum2 = true;
    }

    public void funcEquals(View view) {
        if(num2str != "")
            num2 = Integer.parseInt(num2str);

        if(num2 == 0 && operator.equals("/")) {
            resultView.setText("Can't divide by zero.");
            endOfCalc = true;
            return;
        }

        if(expressionView.getText().toString().equals("")){
            endOfCalc=true;
            return;
        }

        switch (operator){
            case "+": resultView.setText("= " + Integer.toString(num1 + num2));endOfCalc = true; break;
            case "-": resultView.setText("= " + Integer.toString(num1 - num2));endOfCalc = true; break;
            case "/": resultView.setText("= " + Float.toString((float)num1 / num2));endOfCalc = true; break;
            case "*": resultView.setText("= " + Integer.toString(num1 * num2));endOfCalc = true; break;
            case "": resultView.setText("= " + expressionView.getText().toString());endOfCalc = true; break;
        }
    }

    public void clearCalc(View view) {
        expressionView.setText("");
        resultView.setText("");
        num1=0;
        num2=0;
        num2str = "";
        writingToNum2 = false;
        operator = "";
        endOfCalc = false;
        numberOfOperators = 0;
    }
}