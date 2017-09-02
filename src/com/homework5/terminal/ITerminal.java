package com.homework5.terminal;


import javax.management.BadStringOperationException;
import javax.security.auth.login.LoginException;
import java.util.zip.DataFormatException;

public interface ITerminal {

    float getBalance(String pin);

    boolean withdrow(String pin, float sum) throws DataFormatException, BadStringOperationException;

    boolean add(String pin, float sum);
}
