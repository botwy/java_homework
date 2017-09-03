package com.homework5.terminal;

import javax.management.BadStringOperationException;
import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.LoginException;
import java.net.SocketException;
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
        if (checkPin(pin)) {
            try {
                float bal = server.getBalance();
                ui.showBalance(bal);
                return bal;
            } catch (SocketException e) {
                e.printStackTrace();
                ui.showSocketException();
                return -1f;
            }
        }else return -1f;
    }

    @Override
    public boolean withdrow(String pin, float sum) {
        if(sum%100f>0) {
            try {
                throw new DataFormatException("Sum not multiple 100 rub. Enter sum multiple 100 rub");
            } catch (DataFormatException e) {
                e.printStackTrace();
                ui.showWrongMultipleSum();
            }
            return false;
        }
        if (checkPin(pin)) {
            try {
                    server.withdrow(sum);
                    ui.showWithdrowDone(sum);
            } catch (SocketException e) {
                e.printStackTrace();
                ui.showSocketException();
            } catch (BadStringOperationException e) {
                e.printStackTrace();
                ui.showWrongSumException();
            }
            return true;
        }
        else return false;
    }

    @Override
    public boolean add(String pin, float sum) {
        if (checkPin(pin)) {
            try {
                server.add(sum);
                ui.showAddMoneyDone(sum);
            } catch (SocketException e) {
                e.printStackTrace();
                ui.showSocketException();
            }
            return true;
        }
        else return false;
    }

    private boolean checkPin(String pin) {
        if (pin == null || pin.equals("") || pin.equals("0")) {
            try {
                throw new LoginException("there is no PIN. Need enter pin for all transaction");
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
                    throw new AccountLockedException("pin is locked, time to unlocked " + time_to_unlock+" sec. Over this time enter pin");
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
                    throw new AccountLockedException("pin is locked, time to unlocked " + time_to_unlock+" sec. Over this time enter right pin.");
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

