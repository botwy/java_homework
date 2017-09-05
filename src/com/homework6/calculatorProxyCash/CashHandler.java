package com.homework6.calculatorProxyCash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CashHandler implements InvocationHandler {

    private final Object delegate;

    public CashHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        float result;
        float operand1 = (float) args[0];
        float operand2 = (float) args[1];
        String operator = (String) args[2];
        FileReader fr = new FileReader("C:\\Users\\denis\\Documents\\JavaSchool\\1.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\\t");
            if (arr.length == 0 || arr.length == 1) continue;
            if (Float.toString(operand1).equals(arr[1]) && Float.toString(operand2).equals(arr[2]) && operator.equals(arr[0])) {
                result = Float.parseFloat(arr[3]);
                return result;
            }
        }
        result = (float) method.invoke(delegate, args);
        System.out.println(args[0] + " " + args[1] + " " + args[2]);

        writeInFile(operand1, operand2, operator, result);
        return result;
    }

    public void writeInFile(float operand1, float operand2, String operator, float result) {
        String operand1_text = Float.toString(operand1);
        String operand2_text = Float.toString(operand2);

        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\denis\\Documents\\JavaSchool\\1.txt", true);
            fileWriter.write("\n" + operator + "\t" + operand1_text
                    + "\t" + operand2_text
                    + "\t" + result
            );
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
