package com.homework5.terminal;

import java.util.Scanner;

public class UI implements UserInterface {

    private String out_text = "";

    //static get pin from IO
    public static String firstRequestPin(String msg) {
        System.out.println(msg);
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    //if pin is wrong ask else (NONstatic)
    public String requestPinAfterWrong(String msg) {
        System.out.println(msg);
        Scanner sc = new Scanner(System.in);
        out_text = sc.next();
        return out_text;
    }

    @Override
    public void showBalance(float sum) {
        System.out.println("Ваш баланс: " + sum + " руб");
    }

    @Override
    public void showWithdrowDone(float sum) {
        System.out.println("Перевод выполнен на сумму " + sum + " руб");
    }

    @Override
    public void showAddMoneyDone(float sum) {
        System.out.println("Баланс пополнен на " + sum + " руб");
    }

    @Override
    public void showSocketException() {
        System.out.println("Запрос не выполнен из-за проблем со связью");
    }

    @Override
    public void showWrongSumException() {
        System.out.println("Запрос не выполнен, недостаточно средств");
    }

    @Override
    public void showWrongMultipleSum() {
        System.out.println("Запрос не выполнен, сумма должна быть кратна 100 рублям");
    }


}
