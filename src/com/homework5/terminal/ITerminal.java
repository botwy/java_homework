package com.homework5.terminal;

/**
 * интерфейс ITerminal, c помощью которого можно:
 * 1) Проверить состояние счета
 * 2) Снять/ положить деньги
 */
public interface ITerminal {

    float getBalance(String pin);

    boolean withdrow(String pin, float sum);

    boolean add(String pin, float sum);
}
