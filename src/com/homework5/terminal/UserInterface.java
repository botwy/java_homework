package com.homework5.terminal;

public interface UserInterface {
    void showBalance(float sum);

    void showWithdrowDone(float sum);

    void showAddMoneyDone(float sum);

    void showSocketException();

    void showWrongSumException();

    void showWrongMultipleSum();
}
