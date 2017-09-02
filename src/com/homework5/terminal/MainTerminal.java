package com.homework5.terminal;

import jdk.nashorn.internal.ir.Terminal;

import javax.management.BadStringOperationException;
import javax.security.auth.login.LoginException;
import java.util.zip.DataFormatException;

public class MainTerminal {

    public static void main(String[] args) {
        TerminalImpl term = new TerminalImpl();
        String pin = "1234";

            float bal = term.getBalance(pin);
            System.out.println(bal);

        try {
            term.withdrow(pin,9000f);
            System.out.println("money withdrow");
        } catch (DataFormatException e) {
            e.printStackTrace();
        } catch (BadStringOperationException e) {
            e.printStackTrace();
        }
        bal = term.getBalance(pin);
        System.out.println(bal);

    }

}
