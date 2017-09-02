package com.homework5.terminal;

import jdk.nashorn.internal.ir.Terminal;

import javax.management.BadStringOperationException;
import javax.security.auth.login.LoginException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class MainTerminal {

    public static void main(String[] args) {
        TerminalImpl term = new TerminalImpl();


            float bal = term.getBalance(UI.firstRequestPin("Чтобы узнать баланс введите пин"));
            System.out.println(bal);

        try {
            System.out.println("Введите сумму которую хотите отправить:");
            Scanner sc = new Scanner(System.in);
            float sum = sc.nextFloat();
            term.withdrow(UI.firstRequestPin("Чтобы отправить "+sum+"руб ведите пин"),sum);
        } catch (DataFormatException e) {
            e.printStackTrace();
        } catch (BadStringOperationException e) {
            e.printStackTrace();
        }
        bal = term.getBalance(UI.firstRequestPin("Чтобы узнать баланс ведите пин"));
        System.out.println(bal);

    }

}
