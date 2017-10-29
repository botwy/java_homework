package com.homework5.terminal;

import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;
import com.sun.xml.internal.ws.spi.db.DatabindingException;

import javax.management.BadStringOperationException;
import java.net.ConnectException;
import java.net.SocketException;

/**
 *  Получает запросы от банкоматов (терминалов), связывается с базой данных, выполняет операции, возвращает результат
 *  TerminalServer может кидать исключения связанные с проблемами сети; когда недостаточно денег чтобы их снять;
 */
public class TerminalServer {

    private float balance = 9500f;
    private boolean netProblem = false;

    public void setNetProblem(boolean netProblem) {
        this.netProblem = netProblem;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

/**
 * @exception ConnectException if can't connect to database
 */
    public float getBalance() throws ConnectException {
        if (!netProblem)
            return balance;
        else throw new ConnectException("Communication DataBase error");
    }

    /**
     * @exception BadStringOperationException if enter sum > balance
     * @exception  ConnectException if can't connect to database
     */
    public boolean withdrow(float sum) throws BadStringOperationException, ConnectException {
        if (netProblem) throw new ConnectException("Communication DataBase error");
        if (balance >= sum) {
            balance -= sum;
            return true;
        } else {
            throw new BadStringOperationException("Not enough fund. Enter sum <= balance");
        }
    }

    /**
     * @exception ConnectException if can't connect to database
     */
    public boolean add(float sum) throws ConnectException {
        if (netProblem) throw new ConnectException("Communication DataBase error");
        balance += sum;
        return true;
    }

    /**
     * @exception ConnectException if can't connect to database
     */
    public boolean checkEnoughBalance(float request_sum) throws ConnectException {
        if (netProblem) throw new ConnectException("Communication DataBase error");
        if (request_sum <= balance) return true;
        else return false;
    }
}
