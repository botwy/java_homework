package com.homework5.terminal;

import javax.management.BadStringOperationException;
import java.net.SocketException;
import java.util.concurrent.ExecutionException;

public class TerminalServer{

private float balance = 9500f;
private boolean netProblem = false;

    public void setNetProblem(boolean netProblem) {
        this.netProblem = netProblem;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getBalance() throws SocketException {
        if (!netProblem)
        return balance;
        else throw new SocketException();
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
        if (request_sum<=balance) return true;
        else return false;
    }
}
