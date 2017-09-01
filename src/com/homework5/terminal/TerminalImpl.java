package com.homework5.terminal;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class TerminalImpl implements ITerminal {
    private final TerminalServer server = new TerminalServer();
    private final PinValidator pinValidator = new PinValidator();
    private int countWrongTry = 0;
    private boolean isLocked = false;
    private long lockTimeMilis = 0;

    @Override
    public float getBalance(String pin) {
        if (checkPin(pin))
            return server.getBalance();

        return -1f;
    }

    @Override
    public boolean withdrow(String pin, float sum) {
        return false;
    }

    @Override
    public boolean add(String pin, float sum) {
        return false;
    }

    private boolean checkPin(String pin) {
        if (pin == null || pin.equals("") || pin.equals("0")) {
            try {
                throw new LoginException("there is no PIN");
            } catch (LoginException e) {
                e.printStackTrace();
            } finally {
                System.out.println("pin is null, type true pin");
                Scanner sc = new Scanner(System.in);
                String in_text = sc.next();

                return checkPin(in_text);
            }
        } else if (pinValidator.checkPin(pin) && !isLocked) return true;
        else {
            if (!isLocked) countWrongTry++;
            else if (System.currentTimeMillis() - lockTimeMilis > 5000)
                isLocked = false;
            else {
                long time_to_unlock = (5000 + lockTimeMilis - System.currentTimeMillis()) / 1000;
                try {
                    throw new AccountLockedException("pin is locked, time to unlocked " + time_to_unlock);
                } catch (AccountLockedException e) {
                    e.printStackTrace();
                }
            }
            if (countWrongTry == 3) {
                isLocked = true;
                lockTimeMilis = System.currentTimeMillis();
                countWrongTry = 0;
            }
            System.out.println("pin is wrong, type true pin");
            Scanner sc = new Scanner(System.in);
            String in_text = sc.next();

            return checkPin(in_text);

        }
    }
}

