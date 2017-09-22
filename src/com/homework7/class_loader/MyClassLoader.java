package com.homework7.class_loader;

import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader extends URLClassLoader {
    private String notDelegateClassName;

    public MyClassLoader(URL[] urls, String notDelegateClassName ) {
        super(urls);
        this.notDelegateClassName=notDelegateClassName;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
      //  System.out.println(name);
        if(name.equals(notDelegateClassName))
            return super.findClass(name);
        else
       return super.loadClass(name);


    }

    @Override
    public URL getResource(String name) {
        return super.findResource(name);
    }
}
