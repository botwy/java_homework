package com.homework6.BeanUtils;

import java.lang.reflect.InvocationTargetException;
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
        Method[] arr_meth_from = from.getClass().getMethods();
        List<Method> list_geterFrom = new ArrayList<>();
        for (int i = 0; i < arr_meth_from.length; i++) {
           // System.out.println(arr_meth[i].getName().substring(0,3));
           if (arr_meth_from[i].getName().substring(0,3).equals("get")) {
               list_geterFrom.add(arr_meth_from[i]);

           }
        }

        Method[] arr_meth_to = to.getClass().getMethods();
        for (int i = 0; i < arr_meth_to.length; i++) {
            // System.out.println(arr_meth[i].getName().substring(0,3));
            if (arr_meth_to[i].getName().substring(0,3).equals("set"))
                for (int j = 0; j < list_geterFrom.size(); j++) {
                Method curr_methFrom = list_geterFrom.get(j);
                        Method curr_methTo =  arr_meth_to[i];
                int meth_fromNL = curr_methFrom.getName().length();
                int meth_toNL = curr_methTo.getName().length();
                    if (curr_methFrom.getName().substring(3,meth_fromNL).equals(
                            curr_methTo.getName().substring(3,meth_toNL)))
                        try {
                        Class<?> param_typeTo = curr_methTo.getParameterTypes()[0];
                            Class<?> return_typeFrom = curr_methFrom.getReturnType();
                      if (param_typeTo.equals(return_typeFrom) || param_typeTo.isAssignableFrom(return_typeFrom))
                            curr_methTo.invoke(to,curr_methFrom.invoke(from));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }

                }

        }
    }
}
