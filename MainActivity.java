package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newnum;
    private TextView displayOperation;
    //OPERANDS and TYPE of Calculations

    private Double operand1 = null;
    private String pendingOperation = "=";
    private static final  String STATE_PENDING_OPERATION = "pendingOperation";
    private static final String STATE_OPERAND1="operand1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declariton
        result = (EditText) findViewById(R.id.result);
        newnum = (EditText) findViewById(R.id.newnum);
        displayOperation = (TextView) findViewById(R.id.operation);

        //Assign Buttons

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttondot = (Button) findViewById(R.id.buttondot);


        Button buttondiv = (Button) findViewById(R.id.buttondiv);
        Button buttonmult = (Button) findViewById(R.id.buttonmult);
        Button buttonplus = (Button) findViewById(R.id.buttonplus);
        Button buttonminus = (Button) findViewById(R.id.buttonminus);
        Button buttonequals = (Button) findViewById(R.id.buttonequals);

        //setup Listener


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newnum.append(b.getText().toString());

            }
        };


        //button set Up Listen

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttondot.setOnClickListener(listener);

        View.OnClickListener oplistner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newnum.getText().toString();

                try {
                    double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                } catch (NumberFormatException e) {
                    newnum.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);

            }

        };
        buttonequals.setOnClickListener(oplistner);
        buttondiv.setOnClickListener(oplistner);
        buttonminus.setOnClickListener(oplistner);
        buttonmult.setOnClickListener(oplistner);
        buttonplus.setOnClickListener(oplistner);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1);
        }
        super.onSaveInstanceState(outState);

    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation=savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1=savedInstanceState.getDouble(STATE_OPERAND1);
        displayOperation.setText(pendingOperation);

    }

    private void performOperation(Double value, String operation) {
        if (null == operand1) {
            operand1 = value;

        } else {

            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (operation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (value == 0) {
                        operand1 = 0.0;
                    }
                    break;
                case "+":
                    operand1 += value;
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
            }

        }
        result.setText(operand1.toString());
        newnum.setText("");
    }

}
