package com.homefork7.class_loader;

import com.plugins.encrypted_classes.HelloEncr;

import java.io.File;

public class MainClassLoader {

    public static void main(String[] args) {
        PluginManager pl_manager = new PluginManager("out\\production\\java_homework\\com\\plugins");
        Plugin pl_hello1 = pl_manager.load("user1", "com.plugins.user1.Hello");
        Plugin pl_hello2 = pl_manager.load("user2", "com.plugins.user2.Hello");
        pl_hello1.invoke();
        pl_hello2.invoke();

        ClassLoader encrClassLoader = new EncryptedClassLoader("0",new File("out\\production\\java_homework\\com\\plugins\\encrypted_classes"),null);
        try {
        Class cl = encrClassLoader.loadClass("com.plugins.encrypted_classes.HelloEncr");
       System.out.println(cl);
        // HelloEncr o = (HelloEncr) cl.newInstance();
       //  o.invoke();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
