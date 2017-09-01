package com.homework5.terminal;

import jdk.nashorn.internal.ir.Terminal;

import javax.security.auth.login.LoginException;

public class MainTerminal {

    public static void main(String[] args) {
        TerminalImpl term = new TerminalImpl();
        String pin = "1234";
        try {
            float bal = term.getBalance(pin);
            System.out.println(bal);
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

}
