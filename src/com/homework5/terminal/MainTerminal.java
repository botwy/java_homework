package com.homework5.terminal;

import jdk.nashorn.internal.ir.Terminal;

import javax.management.BadStringOperationException;
import javax.security.auth.login.LoginException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class MainTerminal {

    public static void main(String[] args) {
        TerminalImpl term = new TerminalImpl();
        Scanner sc = new Scanner(System.in);
        float sum = 0f;
        term.getBalance(UI.firstRequestPin("Чтобы узнать баланс введите пин"));


        System.out.println("Введите сумму которую хотите отправить:");

        sum = sc.nextFloat();
        term.withdrow(UI.firstRequestPin("Чтобы отправить "+sum+" руб ведите пин"),sum);

        term.getBalance(UI.firstRequestPin("Чтобы узнать баланс ведите пин"));

        System.out.println("Введите сумму которую хотите положить на свой счет");
        sum = sc.nextFloat();
        term.add(UI.firstRequestPin("Чтобы положить на счет "+sum+" руб ведите пин"),sum);

        term.getBalance(UI.firstRequestPin("Чтобы узнать баланс ведите пин"));
    }

}
