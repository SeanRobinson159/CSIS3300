package edu.csis4850.srobinson.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    private EditText display;
    private TextView history;
    private double total;
    private double num1;
    private double num2;
    private String operation;
    private boolean isDecimal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        total = 0;
        display = (EditText) findViewById(R.id.display);
        display.setKeyListener(null);
        history = (TextView) findViewById(R.id.history);


        findViewById(R.id.AC).setOnClickListener(this);
        findViewById(R.id.root).setOnClickListener(this);
        findViewById(R.id.squared).setOnClickListener(this);
        findViewById(R.id.ans).setOnClickListener(this);

        findViewById(R.id.pi).setOnClickListener(this);
        findViewById(R.id.tan).setOnClickListener(this);
        findViewById(R.id.cos).setOnClickListener(this);
        findViewById(R.id.sin).setOnClickListener(this);

        findViewById(R.id.factorial).setOnClickListener(this);
        findViewById(R.id.percent).setOnClickListener(this);
        findViewById(R.id.log).setOnClickListener(this);
        findViewById(R.id.ln).setOnClickListener(this);


        findViewById(R.id.equals).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.subtract).setOnClickListener(this);
        findViewById(R.id.multiply).setOnClickListener(this);
        findViewById(R.id.divide).setOnClickListener(this);
        findViewById(R.id.decimal).setOnClickListener(this);


        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.zero).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.AC:
                display.setText("0");
                history.setText("");
                total = 0;
                break;

            case R.id.sin:
                num1 = Double.parseDouble(display.getText().toString());
                history.setText("sin("+num1+")=");
                isDecimal = false;
                total = Math.sin(Math.toRadians(num1));
                display.setText(total+"");
                break;
            case R.id.cos:
                num1 = Double.parseDouble(display.getText().toString());
                history.setText("cos("+num1+")=");
                isDecimal = false;
                total = Math.cos(Math.toRadians(num1));
                display.setText(total+"");
                break;
            case R.id.tan:
                num1 = Double.parseDouble(display.getText().toString());
                history.setText("tan("+num1+")=");
                isDecimal = false;
                total = Math.tan(Math.toRadians(num1));
                display.setText(total+"");
                break;
            case R.id.pi:
                updateNumber(Math.PI);
                break;

            case R.id.factorial:
                num1 = Double.parseDouble(display.getText().toString());
                history.setText(num1+"!=");
                total = num1;
                for(int i = (int)num1; i>2; i-- ){
                    total = total*(i-1);
                }
                display.setText(total+"");
                break;
            case R.id.percent:
                num1 = Double.parseDouble(display.getText().toString());
                history.setText(num1+"%=");
                total = num1/100;
                display.setText(total+"");
                break;

            case R.id.log:
                break;
            case R.id.ln:
                break;
            case R.id.ans:
                break;

            case R.id.squared:
                num1 = Double.parseDouble(display.getText().toString());
                history.setText(num1+"²=");
                total = Math.pow(num1,2);
                display.setText(total+"");
                break;
            case R.id.root:
                num1 = Double.parseDouble(display.getText().toString());
                history.setText("√("+num1+")=");
                total = Math.sqrt(num1);
                display.setText(total+"");
                break;






            case R.id.decimal:
                isDecimal = true;
                break;







            case R.id.nine:
                updateNumber(9);
                break;
            case R.id.eight:
                updateNumber(8);
                break;
            case R.id.seven:
                updateNumber(7);
                break;
            case R.id.six:
                updateNumber(6);
                break;
            case R.id.five:
                updateNumber(5);
                break;
            case R.id.four:
                updateNumber(4);
                break;
            case R.id.three:
                updateNumber(3);
                break;
            case R.id.two:
                updateNumber(2);
                break;
            case R.id.one:
                updateNumber(1);
                break;
            case R.id.zero:
                updateNumber(0);
                break;

            case R.id.add:
                num1 = Double.parseDouble(display.getText().toString());
                history.setText(num1+"+");
                operation = "add";
                isDecimal = false;
                display.setText("0");
                break;
            case R.id.subtract:
                num1 = Double.parseDouble(display.getText().toString());
                history.setText(num1+"-");
                operation = "subtract";
                isDecimal = false;
                display.setText("0");
                break;
            case R.id.multiply:
                num1 = Double.parseDouble(display.getText().toString());
                history.setText(num1+"×");
                operation = "multiply";
                isDecimal = false;
                display.setText("0");
                break;
            case R.id.divide:
                num1 = Double.parseDouble(display.getText().toString());
                history.setText(num1+"÷");
                operation = "divide";
                isDecimal = false;
                display.setText("0");
                break;


            case R.id.equals:
                num2 = Double.parseDouble(display.getText().toString());
                history.setText(history.getText().toString()+total+"=");
                if(operation == "add") {
                    total = num1 + num2;
                } else if(operation == "subtract"){
                    total = num1 - num2;
                } else if(operation == "multiply"){
                    total = num1*num2;
                } else if(operation == "divide"){
                    total = num1/num2;
                }
                display.setText(total+"");
                isDecimal = false;
                break;
        }
    }

    public void updateNumber(double number){
        total = Double.parseDouble(display.getText().toString());
        if(isDecimal){
            total = total + number/10;
        } else {
            total = total * 10 + number;
        }
        display.setText(total+"");
    }
}
