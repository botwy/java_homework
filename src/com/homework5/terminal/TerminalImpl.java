package com.homework5.terminal;

import javax.management.BadStringOperationException;
import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.LoginException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class TerminalImpl implements ITerminal {
    private final TerminalServer server = new TerminalServer();
    private final PinValidator pinValidator = new PinValidator();

    private final UI ui = new UI();

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
    public boolean withdrow(String pin, float sum) throws DataFormatException, BadStringOperationException {
        float modulo = sum%100f;
        if(modulo>0)
            throw new DataFormatException("Sum must min 100");

        if (checkPin(pin)) {
            if (server.withdrow(sum)) {
                ui.showWithdrowDone(sum);
                return true;
            } else return false;
        }
        else return false;
    }

    @Override
    public boolean add(String pin, float sum) {
        if (checkPin(pin))
        return server.add(sum);
        else return false;
    }

    private boolean checkPin(String pin) {
        if (pin == null || pin.equals("") || pin.equals("0")) {
            try {
                throw new LoginException("there is no PIN");
            } catch (LoginException e) {
                e.printStackTrace();
                String in_text = ui.requestPinAfterWrong("Пин неверный, введите верный пин");
                return checkPin(in_text);
            }
        } else if (pinValidator.checkPin(pin)) {
            if (System.currentTimeMillis() - lockTimeMilis > 5000)
                isLocked = false;
            if (isLocked) {
                long time_to_unlock = (5000 + lockTimeMilis - System.currentTimeMillis()) / 1000;
                try {
                    throw new AccountLockedException("pin is locked, time to unlocked " + time_to_unlock+" sec");
                } catch (AccountLockedException e) {
                    e.printStackTrace();
                    String in_text = ui.requestPinAfterWrong("Подождите "+time_to_unlock+" сек и введите пин");
                    return checkPin(in_text);
                }
            }else {
                countWrongTry = 0;
                return true;
            }
        }
        else {
            if (!isLocked) countWrongTry++;
            else if (System.currentTimeMillis() - lockTimeMilis > 5000)
                isLocked = false;
            else {
                long time_to_unlock = (5000 + lockTimeMilis - System.currentTimeMillis()) / 1000;
                try {
                    throw new AccountLockedException("pin is locked, time to unlocked " + time_to_unlock+" sec");
                } catch (AccountLockedException e) {
                    e.printStackTrace();
                    String in_text = ui.requestPinAfterWrong("Ввод пина заблокирован, можно будет продолжить через "+time_to_unlock+" сек");
                    return checkPin(in_text);
                }
            }
            if (countWrongTry == 3) {
                isLocked = true;
                lockTimeMilis = System.currentTimeMillis();
                countWrongTry = 0;
            }
            String in_text = ui.requestPinAfterWrong("Пин неверный, введите верный пин");
            return checkPin(in_text);

        }
    }
}

