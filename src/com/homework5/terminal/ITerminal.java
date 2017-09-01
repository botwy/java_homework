package com.homework5.terminal;


import javax.security.auth.login.LoginException;

public interface ITerminal {

    float getBalance(String pin);

    boolean withdrow(String pin, float sum);

    boolean add(String pin, float sum);
}
