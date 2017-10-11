package com.homework6.calculatorProxyCash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CashHandler implements InvocationHandler {

    private final Object delegate;
    private final HashMap<Element, Float> cache_map = new HashMap<Element, Float>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock r = readWriteLock.readLock();
    private final Lock w = readWriteLock.writeLock();

    public CashHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Element ask_key = new Element((float) args[0], (float) args[1], (String) args[2]);
        Lock lock = new ReentrantLock();

        w.lock();
        try {
            if (!cache_map.containsKey(ask_key)) {

                cache_map.put(ask_key, (float) method.invoke(delegate, args));
            }

        } finally {
            w.unlock();
        }


        r.lock();
        try {
            return cache_map.get(ask_key);
        } finally {
            r.unlock();
        }


    }


}
