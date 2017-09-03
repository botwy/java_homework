package com.homework5.terminal;


public interface ITerminal {

    float getBalance(String pin);

    boolean withdrow(String pin, float sum);

    boolean add(String pin, float sum);
}
