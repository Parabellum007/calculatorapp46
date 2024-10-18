
package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentNumber = "";
    private String operator = "";
    private double firstOperand = 0;
    private boolean operatorSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
    }

    // When number buttons are clicked
    public void onNumberClick(View view) {
        Button button = (Button) view;
        currentNumber += button.getText().toString();
        display.setText(currentNumber);
    }

    // When operator buttons are clicked
    public void onOperatorClick(View view) {
        if (!operatorSelected) {
            firstOperand = Double.parseDouble(currentNumber);
            operator = ((Button) view).getText().toString();
            currentNumber = "";
            operatorSelected = true;
        }
    }

    // When equal button is clicked
    public void onEqualClick(View view) {
        if (!operatorSelected || currentNumber.equals("")) {
            return;
        }

        double secondOperand = Double.parseDouble(currentNumber);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    display.setText("Error");
                    return;
                }
                break;
        }

        display.setText(String.valueOf(result));
        currentNumber = String.valueOf(result);
        operatorSelected = false;
    }

    // When clear button is clicked
    public void onClearClick(View view) {
        currentNumber = "";
        operator = "";
        firstOperand = 0;
        operatorSelected = false;
        display.setText("0");
    }
}
