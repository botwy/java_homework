package com.homework5.terminal;

import javax.management.BadStringOperationException;
import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.zip.DataFormatException;


/**
 * Доступ к терминалу должен предоставлять только после корректного ввода пина. При попытке вызова любого метода без ввода пина, должен кидаться ексепшен. 
 * При вводе 3 неправильных пинов, аккаунт лочится на 5сек( при попытке обраться к нему вылетает AccountIsLockedException c информацией о времени разлочения).
 * Класть и снимать деньги можно только, если сумма кратна 100.
 * Банкоматы всего лишь делают проверку введенных данных и отправляют запросы на удаленный сервер(TerminalServer). 
 */
public class TerminalImpl implements ITerminal {
    private final PinValidator pinValidator = new PinValidator();

    private final UI ui = new UI();

    private int countWrongTry = 0;
    private boolean isLocked = false;
    private long lockTimeMilis = 0;

    //connect and get current server-object
    private TerminalServer getServer() {
        TerminalServer server = null;
        try (Socket socket = new Socket("localhost", 3000)) {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            server = (TerminalServer) ois.readObject();
            ois.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return server;
        }
    }

    @Override
    public float getBalance(String pin) {
        TerminalServer server = getServer();
        if (checkPin(pin)) {
            try {

                if (server == null) return -1f;

                float bal = server.getBalance();
                ui.showBalance(bal);
                return bal;
            } catch (ConnectException e) {
                e.printStackTrace();
                ui.showSocketException();
                return -1f;
            }
        } else return -1f;
    }

    @Override
    public boolean withdrow(String pin, float sum) {
        TerminalServer server = getServer();

        if (server == null) return false;

        if (sum % 100f > 0) {
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
            } catch (ConnectException e) {
                e.printStackTrace();
                ui.showSocketException();
            } catch (BadStringOperationException e) {
                e.printStackTrace();
                ui.showWrongSumException();
            }
            return true;
        } else return false;
    }

    @Override
    public boolean add(String pin, float sum) {
        if (sum % 100f > 0) {
            try {
                throw new DataFormatException("Sum not multiple 100 rub. Enter sum multiple 100 rub");
            } catch (DataFormatException e) {
                e.printStackTrace();
                ui.showWrongMultipleSum();
            }
            return false;
        }

        if (checkPin(pin)) {
            TerminalServer server = getServer();
            if (server == null) return false;
            try {
                server.add(sum);
                ui.showAddMoneyDone(sum);
            } catch (ConnectException e) {
                e.printStackTrace();
                ui.showSocketException();
            }
            return true;
        } else return false;
    }

    //checking pin code
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
                    throw new AccountLockedException("pin is locked, time to unlocked " + time_to_unlock + " sec. Over this time enter pin");
                } catch (AccountLockedException e) {
                    e.printStackTrace();
                    String in_text = ui.requestPinAfterWrong("Подождите " + time_to_unlock + " сек и введите пин");
                    return checkPin(in_text);
                }
            } else {
                countWrongTry = 0;
                return true;
            }
        } else {
            if (!isLocked) countWrongTry++;
            else if (System.currentTimeMillis() - lockTimeMilis > 5000)
                isLocked = false;
            else {
                long time_to_unlock = (5000 + lockTimeMilis - System.currentTimeMillis()) / 1000;
                try {
                    throw new AccountLockedException("pin is locked, time to unlocked " + time_to_unlock + " sec. Over this time enter right pin.");
                } catch (AccountLockedException e) {
                    e.printStackTrace();
                    String in_text = ui.requestPinAfterWrong("Ввод пина заблокирован, можно будет продолжить через " + time_to_unlock + " сек");
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

