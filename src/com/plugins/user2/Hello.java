package com.plugins.user2;

import com.homefork7.class_loader.Plugin;

public class Hello implements Plugin {
    @Override
    public void invoke() {
        System.out.println("user2 hello");
    }
}
