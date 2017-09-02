package com.homework5.terminal;

import javax.management.BadStringOperationException;
import java.util.concurrent.ExecutionException;

public class TerminalServer{

private float balance = 9500f;

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean withdrow(float sum) throws BadStringOperationException {
        if(balance>=sum) {
            balance -= sum;
            return true;
        }else{
            throw new BadStringOperationException("Not enough fund");
        }
    }

    public boolean add(float sum) {
        balance+=sum;
        return true;
    }


    public boolean checkEnoughBalance(float request_sum) {
        if (request_sum<=10000f) return true;
        else return false;
    }
}
