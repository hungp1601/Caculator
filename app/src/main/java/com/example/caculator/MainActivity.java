package com.example.caculator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6,
            btn7, btn8, btn9, btnPlus, btnSub, btnDiv, btnMul, btnClear, btnEqual, btnDot;

    TextView answer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();

        // display and error handles
        btn1.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "1");
        });

        btn2.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "2");
        });

        btn3.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "3");
        });

        btn4.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "4");
        });

        btn5.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "5");
        });
        btn6.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "6");
        });
        btn7.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "7");
        });
        btn8.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "8");
        });
        btn9.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "9");
        });
        btn0.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "0");
        });
        btnDot.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + ".");
        });
        btnClear.setOnClickListener(v -> Clear());
        btnPlus.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "+");

        });
        btnSub.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "-");
        });
        btnDiv.setOnClickListener(v -> {
            answer.setText(answer.getText().toString() + "/");
        });
        btnMul.setOnClickListener(v -> { answer.setText(answer.getText().toString() + "*"); });

        btnEqual.setOnClickListener(v -> {
            Double finalAns = null;

            try {
                finalAns = getAns();
            } catch (ScriptException e) {
                answer.setText(e.toString());
            }
            if (finalAns != null) {
                long lfinalAns = finalAns.longValue();
                if(lfinalAns == finalAns)
                    answer.setText(String.valueOf(lfinalAns));
                else answer.setText(String.valueOf(finalAns));
            } else {
                ErrorMessage();
                Clear();
            }
        });
    }

    private void getViews() {
        btn0 = (Button) findViewById(R.id.button0);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btnPlus = (Button) findViewById(R.id.buttonPlus);
        btnSub = (Button) findViewById(R.id.buttonSubtract);
        btnDiv = (Button) findViewById(R.id.buttonDivide);
        btnMul = (Button) findViewById(R.id.buttonMultiple);
        btnDot = (Button) findViewById(R.id.buttonDot);
        btnEqual = (Button) findViewById(R.id.buttonEqual);
        btnClear = (Button) findViewById(R.id.buttonClear);
        answer = (TextView) findViewById(R.id.screen);
    }

    //error handle

    void Clear() {
        answer.setText("");
    }

    public void ErrorMessage() {
        Clear();
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage("Error expressions");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, i) -> dialog.dismiss());
        alertDialog.show();
    }

    double getAns() throws ScriptException {
        String s = answer.getText().toString();
        Double result = null;
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        result = (Double) engine.eval(s);
        return result;
    }
}
