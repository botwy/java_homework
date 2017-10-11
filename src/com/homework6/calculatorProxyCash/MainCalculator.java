package com.homework6.calculatorProxyCash;

import com.sun.deploy.net.proxy.ProxyUtils;
import sun.nio.ch.ThreadPool;

import java.io.*;
import java.lang.reflect.Proxy;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert first number");
        float first_number = scanner.nextFloat();

        System.out.println("Insert second number");
        float second_number = scanner.nextFloat();

        System.out.println("Insert operator");
        String operator = scanner.next();

         float result;


        Calculator calc = new Calculator();

        ICalculator cached = MyProxyUtils.makeCached(calc);

        result = cached.calculateResult(first_number,second_number,operator);
        System.out.println("Result = " + result);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5 ; i++) {
            executorService.submit(new Callable<Float>() {
                @Override
                public Float call() throws Exception {
                    float result = cached.calculateResult(1,2,"+");
                    System.out.println(Thread.currentThread()+" Result = "+result);
                    return result;
                }
            });

        }

        executorService.shutdown();






    }

}
