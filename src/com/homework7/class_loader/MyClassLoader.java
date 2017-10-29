package com.homework7.class_loader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Система должна вести себя корректно если в плагине, есть скомпилированные классы с именем, которые есть в браузере(не в плагинах),
 * должны использоваться классы плагина, а не браузера. Для этого изменена модель делегирования класслоадера в методе loadClass
 */
public class MyClassLoader extends URLClassLoader {
    private String notDelegateClassName;

    public MyClassLoader(URL[] urls, String notDelegateClassName ) {
        super(urls);
        this.notDelegateClassName=notDelegateClassName;
    }

    /**
     *  Если пытаемся загрузить класс, который не хотим делегировать, то загружаем текущим MyClassLoader
     *  А родителей (интерфейсы) загружаем с делегированием
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

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
