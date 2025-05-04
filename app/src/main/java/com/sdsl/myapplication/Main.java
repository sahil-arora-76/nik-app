package com.sdsl.myapplication;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Main extends AppCompatActivity {
    EditText display;
    GridLayout grid;

    String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "%", "+",
            "Clear", "ESC", "(", "Result"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        grid = findViewById(R.id.grid);

        for (String label : buttons) {
            Button btn = new Button(this);
            btn.setText(label);
            btn.setTextSize(18);
            btn.setOnClickListener(v -> handleInput(label));
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            btn.setLayoutParams(params);
            grid.addView(btn);
        }
    }

    private void handleInput(String input) {
        String current = display.getText().toString();

        switch (input) {
            case "Clear":
                display.setText("");
                break;
            case "ESC":
                if (!current.isEmpty())
                    display.setText(current.substring(0, current.length() - 1));
                break;
            case "Result":
                try {
                    String expression = current
                            .replaceAll("log", "log10"); // exp4j uses log10

                    Expression exp = new ExpressionBuilder(expression)
                            .functions(
                                    new net.objecthunter.exp4j.function.Function("sin", 1) {
                                        @Override
                                        public double apply(double... args) {
                                            return Math.sin(Math.toRadians(args[0]));
                                        }
                                    },
                                    new net.objecthunter.exp4j.function.Function("cos", 1) {
                                        @Override
                                        public double apply(double... args) {
                                            return Math.cos(Math.toRadians(args[0]));
                                        }
                                    },
                                    new net.objecthunter.exp4j.function.Function("tan", 1) {
                                        @Override
                                        public double apply(double... args) {
                                            return Math.tan(Math.toRadians(args[0]));
                                        }
                                    }
                            )
                            .build();

                    double result = exp.evaluate();
                    display.setText(String.valueOf(result));
                } catch (Exception e) {
                    display.setText("Error");
                }
                break;
            case "sin":
            case "cos":
            case "tan":
            case "log":
                display.append(input + "()");
                display.setSelection(display.getText().length() - 1); // Move cursor inside ()
                break;
            default:
                display.append(input);
        }
    }
}
