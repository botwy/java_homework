package com.homework6.calculatorProxyCache;

import java.util.Scanner;
import java.util.concurrent.*;

public class MainCalculator {

    public static void main(String[] args){

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

        Future<Float> future = executorService.submit(new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                float result = cached.calculateResult(1,2,"+");
                System.out.println(Thread.currentThread()+" Result = "+result);
                return result;
            }
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(future.isDone());
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

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(future.isDone());

        executorService.shutdown();






    }

}
