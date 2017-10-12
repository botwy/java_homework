package com.homework6.calculatorProxyCache;

import java.lang.reflect.Proxy;

public class MyProxyUtils {

    public static ICalculator makeCached(Calculator calculator) {
      ICalculator calc =  (ICalculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader()
                ,new Class[]{ICalculator.class}
                ,new CacheHandler(calculator));

      return calc;
    }
}
