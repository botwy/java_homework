package com.homework5.terminal;

public class TerminalServer{


    public float getBalance() {
        float bal = 9500f;
        return bal;
    }

    public boolean withdrow(float sum) {
        return false;
    }

    public boolean add(float sum) {
        return false;
    }


    public boolean checkEnoughBalance(float request_sum) {
        if (request_sum<=10000f) return true;
        else return false;
    }
}
