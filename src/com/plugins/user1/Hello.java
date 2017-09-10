package com.plugins.user1;

import com.homefork7.class_loader.Plugin;

public class Hello implements Plugin{
    @Override
    public void invoke() {
        System.out.println("user1 hello");
    }
}
