package com.homework7.class_loader;


import com.iplugin.IPlugin;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginManager {

        private final String pluginRootDirectory;

        public PluginManager(String pluginRootDirectory) {
            this.pluginRootDirectory = pluginRootDirectory;
        }

        public IPlugin load(String pluginName, String pluginClassName) {
            //todo
                IPlugin plugin = null;
                File file = new File(this.pluginRootDirectory+"\\"+pluginName+".jar");


                try {
                        URL url_pl =  file.toURI().toURL();
                        MyClassLoader classLoader = new MyClassLoader(new URL[]{url_pl},pluginClassName);
                       Class pl_class = classLoader.loadClass(pluginClassName);
                    //    Class pl_class = ClassLoader.getSystemClassLoader().loadClass(pluginClassName);
                     if(IPlugin.class.isAssignableFrom(pl_class))
                              plugin = (IPlugin) pl_class.newInstance();
                } catch (MalformedURLException e) {
                        e.printStackTrace();
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                } catch (IllegalAccessException e) {
                        e.printStackTrace();
                } catch (InstantiationException e) {
                        e.printStackTrace();
                }
                return plugin;

        }

}
