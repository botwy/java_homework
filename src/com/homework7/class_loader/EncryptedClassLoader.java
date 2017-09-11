package com.homework7.class_loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EncryptedClassLoader extends ClassLoader{

    private final String key;
    private final File dir;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
       // return super.findClass(name);
String[] arr_text = name.split("\\.");

        File[] arr_file = this.dir.listFiles();
        FileInputStream fis;
        for (int i = 0; i < arr_file.length; i++) {
           if (arr_file[i].getName().equals(arr_text[arr_text.length-1]+".class")) {
               try {
                   fis = new FileInputStream(arr_file[i]);
                   byte[] buffer = new byte[fis.available()];
                   fis.read(buffer,0,buffer.length);
                   for (int j = 0; j < buffer.length; j++) {
                      buffer[j]-=Integer.parseInt(this.key);
                   }
                  return defineClass(name,buffer,0,buffer.length);
               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }

        }
        return null;
    }
}
