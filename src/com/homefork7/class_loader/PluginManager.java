package com.homefork7.class_loader;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginManager {

        private final String pluginRootDirectory;

        public PluginManager(String pluginRootDirectory) {
            this.pluginRootDirectory = pluginRootDirectory;
        }

        public Plugin load(String pluginName, String pluginClassName) {
            //todo
                Plugin plugin = null;
                File file = new File(this.pluginRootDirectory+"\\"+pluginName);

                try {
                        URL url_pl =  file.toURI().toURL();
                        URLClassLoader classLoader = new URLClassLoader(new URL[]{url_pl});
                       Class pl_class = classLoader.loadClass(pluginClassName);
                       if(Plugin.class.isAssignableFrom(pl_class))
                              plugin = (Plugin) pl_class.newInstance();
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
