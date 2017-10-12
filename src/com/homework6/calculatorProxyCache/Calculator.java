package com.homework6.calculatorProxyCache;

public class Calculator implements ICalculator {

    // реализация вычисления результата
    @Override
    public float calculateResult(float operand1, float operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
        }
        return 0;
    }
}
