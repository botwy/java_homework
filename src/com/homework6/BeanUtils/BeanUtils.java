package com.homework6.BeanUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {
    /**
          * Scans object "from" for all getters. If object "to"
          * contains correspondent setter, it will invoke it
          * to set property value for "to" which equals to the property
          * of "from".
          * <p/>
          * The type in setter should be compatible to the value returned
          * by getter (if not, no invocation performed).
          * Compatible means that parameter type in setter should
          * be the same or be superclass of the return type of the getter.
          * <p/>
          * The method takes care only about public methods.
          *
          * @param to   Object which properties will be set.
          * @param from Object which properties will be used to get values.
          */
    public static void assign(Object to, Object from) {
        Method[] arr_meth = from.getClass().getMethods();
        List<Method> list_meth_from = new ArrayList<>();
        for (int i = 0; i < arr_meth.length; i++) {
           // System.out.println(arr_meth[i].getName().substring(0,3));
           if (arr_meth[i].getName().substring(0,3).equals("get"))
               list_meth_from.add(arr_meth[i]);

        }

        Method[] arr_meth_to = to.getClass().getMethods();
        for (int i = 0; i < arr_meth_to.length; i++) {
            // System.out.println(arr_meth[i].getName().substring(0,3));
            if (arr_meth_to[i].getName().substring(0,3).equals("set"))
                for (int j = 0; j < list_meth_from.size(); j++) {
                    if (list_meth_from.get(j).getName().substring(3,list_meth_from.get(j).getName().length()).equals(
                            arr_meth_to[i].getName().substring(3,arr_meth_to[i].getName().length())))
                            System.out.println(arr_meth_to[i].getName().substring(3,arr_meth_to[i].getName().length()));

                }

        }
    }
}
