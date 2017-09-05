package com.homework6.calculatorProxyCash;

import com.sun.deploy.net.proxy.ProxyUtils;

import java.io.*;
import java.lang.reflect.Proxy;
import java.util.Scanner;

public class MainCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert first number");
        float first_number = scanner.nextFloat();

        System.out.println("Insert second number");
        float second_number = scanner.nextFloat();

        System.out.println("Insert operator");
        String operator = scanner.next();



      //  float result = execute(first_number, second_number, operator);
       float result;
       //Calculator calculator = new Calculator();
        ICalculator calculator = (ICalculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{ICalculator.class},new CashHandler(new Calculator()));
        result = calculator.calculateResult(first_number,second_number,operator);

        System.out.println("Result = " + result);

       // FileOutputStream fos = null;
       // FileInputStream fin = null;
        /*try {
            fin = new FileInputStream("C:\\Users\\denis\\Documents\\JavaSchool\\1.txt");
            fos = new FileOutputStream("C:\\Users\\denis\\Documents\\JavaSchool\\1.txt");
            byte[] buffer_old = new byte[fin.available()];
            fin.read(buffer_old,0,buffer_old.length);
            System.out.println(buffer_old.length);
            byte[] buffer_new = Float.toString(result).getBytes();
            byte[] buffer_total = new byte[buffer_old.length+buffer_new.length];
            for (int i = 0; i < buffer_old.length; i++) {
                buffer_total[i]=buffer_old[i];
            }
            for (int i = 0; i < buffer_new.length; i++) {
                buffer_total[i+buffer_old.length] = buffer_new[i];
            }
            fos.write(buffer_total);
        } catch (IOException e) {
            e.printStackTrace();
        }*/



    }

/*    private static float execute(float firs_number, float second_number, String operator) {
        switch (operator) {
            case "+":
                return firs_number + second_number;
            case "-":
                return firs_number - second_number;
            case "*":
                return firs_number * second_number;
            case "/":
                return firs_number / second_number;
        }
        return 0;
    }*/
}
