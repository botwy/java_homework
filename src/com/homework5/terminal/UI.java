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

    public void showBalance(float sum) {System.out.println("Ваш баланс: "+sum+" руб");}

    public void showWithdrowDone(float sum){
        System.out.println("Перевод выполнен на сумму "+sum+" руб");
    }

    public void showAddMoneyDone(float sum){
        System.out.println("Баланс пополнен на "+sum+" руб");
    }

    public void showSocketException(){
        System.out.println("Запрос не выполнен из-за проблем со связью");
    }

    public void showWrongSumException(){System.out.println("Запрос не выполнен, недостаточно средств");}

    public void showWrongMultipleSum(){System.out.println("Запрос не выполнен, сумма должна быть кратна 100 рублям");}


}
