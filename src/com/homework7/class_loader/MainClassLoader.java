package com.homework7.class_loader;

//import com.iplugin.IPlugin;

import com.homework7.plugins.encrypted_classes.EncryptedClassLoader;

import java.io.File;

public class MainClassLoader {

    public static void main(String[] args) {

        PluginManager pl_manager = new PluginManager("plugins");

        IPlugin pl_hello3 = pl_manager.load("user3_plugin", "com.homework7.class_loader.Hello");

        IPlugin pl_hello1 = pl_manager.load("user1_plugin", "com.homework7.class_loader.Hello");
   //     Plugin pl_hello1_2 = pl_manager.load("user1", "com.plugins.user1.Hello");
        IPlugin pl_hello2 = pl_manager.load("user2_plugin", "com.homework7.class_loader.Hello");



    Hello hello_new = new Hello();
   // pl_hello1 = (IPlugin)hello_new;

        pl_hello1.invoke();
        pl_hello2.invoke();
    hello_new.invoke();


   // hello_new = (Hello) pl_hello1;
     //   Hello hello1 = (Hello) pl_hello1;
     //   Hello hello1_2 =(Hello) pl_hello1_2;
     //   hello1.invoke();

       System.out.println(pl_hello1.getClass().getClassLoader());

       System.out.println(pl_hello2.getClass().getClassLoader());
        System.out.println(hello_new.getClass().getClassLoader());

        System.out.println(pl_hello1.getClass().getClassLoader().getResource(
                pl_hello1.getClass().getName().replace(".","/")+".class"
        ));
        System.out.println(pl_hello2.getClass().getClassLoader().getResource(
                pl_hello2.getClass().getName().replace(".","/")+".class"
        ));
        System.out.println(hello_new.getClass().getClassLoader().getResource(
                hello_new.getClass().getName().replace(".","/")+".class"
        ));
      //  System.out.println(hello1_2.getClass().getClassLoader());

        ClassLoader encrClassLoader = new EncryptedClassLoader("0",new File("out\\production\\java_homework\\com\\plugins\\encrypted_classes"),null);
        try {
        Class cl = encrClassLoader.loadClass("com.homework7.plugins.encrypted_classes.HelloEncr");
       System.out.println(cl);
       System.out.println(cl.getClassLoader());
        // HelloEncr o = (HelloEncr) cl.newInstance();
       //  o.invoke();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
