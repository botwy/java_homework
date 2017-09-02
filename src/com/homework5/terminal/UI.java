package com.homework5.terminal;

import java.util.Scanner;

public class UI {

    private String out_text="";

    public static String firstRequestPin(String msg) {
        System.out.println(msg);
        Scanner sc = new Scanner(System.in);
        return  sc.next();
    }

    public String requestPinAfterWrong(String msg) {
        System.out.println(msg);
        Scanner sc = new Scanner(System.in);
        out_text = sc.next();
        return  out_text;
    }

    public void showWithdrowDone(float sum){
        System.out.println("Перевод выполнен на сумму "+sum+" руб");
    }

    public void showWrongSumException(String msg){}
}
