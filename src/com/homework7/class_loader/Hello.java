package com.homework7.class_loader;

//import com.iplugin.IPlugin;

public class Hello implements IPlugin{

    @Override
    public void invoke() {
        System.out.println("new hello");
    }
}
