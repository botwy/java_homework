package com.homework5.terminal;

import javax.management.BadStringOperationException;
import java.net.SocketException;

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
        else throw new SocketException("Communication error");
    }


    public boolean withdrow(float sum) throws BadStringOperationException, SocketException {
        if (netProblem) throw new SocketException("Communication error");
        if(balance>=sum) {
            balance -= sum;
            return true;
        }else{
            throw new BadStringOperationException("Not enough fund. Enter sum <= balance");
        }
    }

    public boolean add(float sum) throws SocketException {
        if (netProblem) throw new SocketException("Communication error");
        balance+=sum;
        return true;
    }


    public boolean checkEnoughBalance(float request_sum) throws SocketException {
        if (netProblem) throw new SocketException("Communication error");
        if (request_sum<=balance) return true;
        else return false;
    }
}
