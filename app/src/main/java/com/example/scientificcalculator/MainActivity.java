package com.example.scientificcalculator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtOutput, txtResult;
    Stack<Double> operands = new Stack<>();
    Stack<Character> operators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the TextView for output
        txtOutput = findViewById(R.id.tvOutput);
        txtResult = findViewById(R.id.result);

        // Set click listeners for all the buttons
        Button btnSin = findViewById(R.id.btnSin);
        Button btnCos = findViewById(R.id.btnCos);
        Button btnTan = findViewById(R.id.btnTan);
        Button btnSinH = findViewById(R.id.btnSinH);
        Button btnCosH = findViewById(R.id.btnCosH);
        Button btnTanH = findViewById(R.id.btnTanH);
        Button btnLog = findViewById(R.id.btnLog);
//        Button btnLogTwo = findViewById(R.id.btnLogTwo);
        Button btnE = findViewById(R.id.btnE);
        Button btnSqrt = findViewById(R.id.btnSqrt);
        Button btnPower = findViewById(R.id.btnPower);
        Button btnCut = findViewById(R.id.btnCut);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnBracketOpen = findViewById(R.id.btnBracketOpen);
        Button btnBracketClose = findViewById(R.id.btnBracketClose);
        Button btnPercent = findViewById(R.id.btnPercent);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnSeven = findViewById(R.id.btnSeven);
        Button btnEight = findViewById(R.id.btnEight);
        Button btnNine = findViewById(R.id.btnNine);
        Button btnFour = findViewById(R.id.btnFour);
        Button btnFive = findViewById(R.id.btnFive);
        Button btnSix = findViewById(R.id.btnSix);
        Button btnOne = findViewById(R.id.btnOne);
        Button btnTwo = findViewById(R.id.btnTwo);
        Button btnThree = findViewById(R.id.btnThree);
        Button btnZero = findViewById(R.id.btnZero);
        Button btnDoubleZero = findViewById(R.id.btnDoubleZero);
        Button btnDecimal = findViewById(R.id.btnDecimal);
        Button btnEquals = findViewById(R.id.btnEquals);



        // Set click listeners for all the buttons
        btnSin.setOnClickListener(this);
        btnCos.setOnClickListener(this);
        btnTan.setOnClickListener(this);
        btnSinH.setOnClickListener(this);
        btnCosH.setOnClickListener(this);
        btnTanH.setOnClickListener(this);
        btnLog.setOnClickListener(this);
//        btnLogTwo.setOnClickListener(this);
        btnSqrt.setOnClickListener(this);
        btnPower.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnCut.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnBracketOpen.setOnClickListener(this);
        btnBracketClose.setOnClickListener(this);
        btnPercent.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnDoubleZero.setOnClickListener(this);
        btnDecimal.setOnClickListener(this);
        btnEquals.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // Handle button clicks
        switch (v.getId()) {
            case R.id.btnClear:
                // Clear the output text
                txtOutput.setText("");
                txtResult.setText("");
                break;
            case R.id.btnCut:
                // Clear the output text
                String text = txtOutput.getText().toString();
                if(!text.isEmpty()) {
                    txtOutput.setText(text.substring(0, text.length() - 1));
                    if (Character.isDigit(text.charAt(text.length() - 1))) {
                        double result = evaluateExpression(txtOutput.getText().toString());
                        txtResult.setText(String.valueOf(result));
                    }
                }
                break;
            case R.id.btnEquals:
                // Perform the calculation and update the output
                String expression = txtOutput.getText().toString();
                try {
                    // Evaluate the expression using your preferred method
                    Double result = evaluateExpression(expression);
                    txtOutput.setText(String.valueOf(result));
                    txtResult.setText("");
                } catch (Exception e) {
                    // Handle any exceptions during evaluation

                    txtResult.setText("Error");
                }
                break;
            default:
                // Append the button's text to the output
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                txtOutput.append(buttonText);
                if(Character.isDigit(buttonText.charAt(buttonText.length()-1)) || buttonText.equals(")")){
                    expression = txtOutput.getText().toString();
                    try {
                        // Evaluate the expression using your preferred method
                       Double result = evaluateExpression(expression);
                        txtResult.setText(String.valueOf(result));
                    } catch (Exception e) {
                        // Handle any exceptions during evaluation

                        txtResult.setText("Error");
                    }
                }
                break;
        }
    }

    private double evaluateExpression(String expression) {

        operands = new Stack<>();
        operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                // Extract the entire number
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                i--;

                double num = Double.parseDouble(sb.toString());
                operands.push(num);
            } else if (Character.isLetter(c)) {
                // Extract the entire function name
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && Character.isLetter(expression.charAt(i))) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                i--;

                String functionName = sb.toString();

                if (functionName.equals("Sin")) {
                    operators.push('s');
                } else if (functionName.equals("Cos")) {
                    operators.push('c');
                } else if (functionName.equals("Tan")) {
                    operators.push('t');
                }  else if (functionName.equals("Log")) {
                    operators.push('l');
                }else if (functionName.equals("Sinh")) {
                    operators.push('S');
                } else if (functionName.equals("Cosh")) {
                    operators.push('C');
                } else if (functionName.equals("Tanh")) {
                    operators.push('T');
                } else if (functionName.equals("e")) {
                    operators.push('e');
                } else {
                    throw new IllegalArgumentException("Invalid function: " + functionName);
                }

            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                if(operators.peek()=='('){
                    operators.pop();
                    double result = applyOperator(operators.pop(), operands.pop(), 0);
                    operands.push(result);
                }else {
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        double result = applyOperator(operators.pop(), operands.pop(), operands.pop());
                        operands.push(result);
                    }
                    operators.pop(); // Discard the '(' from the stack
                }

            } else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    double result = applyOperator(operators.pop(), operands.pop(), operands.pop());
                    operands.push(result);
                }
                operators.push(c);
            }

        }

        while (!operators.isEmpty()) {

            double result;
            if(operands.size()==1) {
                result = applyOperator(operators.pop(), operands.pop(), 0);
            }
            else{
                result = applyOperator(operators.pop(), operands.pop(), operands.pop());
            }
            operands.push(result);
        }

        return operands.empty() ?0:operands.pop() ;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/'|| c == '%' || c == '^' || c == '√';
    }

    private int precedence(char operator) {
        if (operator == '+' || operator == '-')
            return 1;
        else if (operator == '*' )
            return 2;
        else if ( operator == '/')
            return 3;
        else if (operator == '%')
            return 4;
        else if (operator == 's' || operator == 'c' || operator == 't' || operator == 'S' || operator == 'C' || operator == 'T' || operator == 'l'|| operator == '^' || operator == '√' || operator == 'e')
            return 5;
        else
            return 0;
    }

    private double applyOperator(char operator, double b, double a) {

        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    txtOutput.setText("Division by zero");
                else
                    return a / b;
            case '%':
                return a*b/100;
            case 's':
                return Math.sin(b);
            case 'c':
                return Math.cos(b);
            case 't':
                return Math.tan(b);
            case 'l':
                return Math.log(b);
            case 'S':
                return Math.sinh(b);
            case 'C':
                return Math.cosh(b);
            case 'T':
                return Math.tanh(b);
            case '^':
                return Math.pow(a,b);
            case '√':
                return Math.sqrt(b);
            case 'e':
                return Math.exp(b);
            default:
                return 0;
        }
    }
}


