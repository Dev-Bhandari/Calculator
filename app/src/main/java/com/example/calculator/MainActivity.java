package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {


    TextView workingTV, resultTV;
    String workings = "", multiply_sign = "×", display = "", formula = "", tempFormula = "";
    boolean leftBracket = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workingTV = findViewById(R.id.idWorkingTV);
        resultTV = findViewById(R.id.idResultTV);

    }

    public void setWorkingTV(String givenValue) {
        if (givenValue == "*") {
            display += multiply_sign;

        } else {
            display += givenValue;
        }
        workings += givenValue;
        workingTV.setText(display);
    }

    public void equals(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double) engine.eval(formula);
        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if (result != null)
            resultTV.setText(String.valueOf(result.doubleValue()));
    }

    private void checkForPowerOf() {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for (int i = 0; i < workings.length(); i++) {
            if (workings.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = workings;
        tempFormula = workings;
        for (Integer index : indexOfPowers) {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index) {
        String numberLeft = "";
        String numberRight = "";

        for (int i = index + 1; i < workings.length(); i++) {
            if (isNumeric(workings.charAt(i)))
                numberRight = numberRight + workings.charAt(i);
            else
                break;
        }

        for (int i = index - 1; i >= 0; i--) {
            if (isNumeric(workings.charAt(i)))
                numberLeft = numberLeft + workings.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow(" + numberLeft + "," + numberRight + ")";
        tempFormula = tempFormula.replace(original, changed);
    }

    private boolean isNumeric(char c) {
        if ((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }


    public void parentheses(View view) {
        if (leftBracket) {
            setWorkingTV("(");
            leftBracket = false;
        } else {
            setWorkingTV(")");
            leftBracket = true;
        }
    }

    public void one(View view) {
        setWorkingTV("1");
    }

    public void two(View view) {
        setWorkingTV("2");
    }

    public void three(View view) {
        setWorkingTV("3");
    }

    public void four(View view) {
        setWorkingTV("4");
    }

    public void five(View view) {
        setWorkingTV("5");
    }

    public void six(View view) {
        setWorkingTV("6");
    }

    public void seven(View view) {
        setWorkingTV("7");
    }

    public void eight(View view) {
        setWorkingTV("8");
    }

    public void nine(View view) {
        setWorkingTV("9");
    }

    public void zero(View view) {
        setWorkingTV("0");
    }

    public void decimal(View view) {
        setWorkingTV(".");
    }

    public void add(View view) {
        setWorkingTV("+");
    }

    public void subtract(View view) {
        setWorkingTV("-");
    }

    public void multiply(View view) {
        setWorkingTV("*");
    }

    public void divide(View view) {
        setWorkingTV("/");
    }

    public void exponent(View view) {
        setWorkingTV("^");
    }


    public void clear(View view) {
        display = "";
        workings = "";
        workingTV.setText("");
        resultTV.setText("");
        leftBracket = true;
    }


}