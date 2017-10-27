package com.homework4.count_map;


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }


    //create and return new ArrayList
    public static List newArrayList() {
        return new ArrayList();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List limit(List<? extends T> source, int size) {
        return source.subList(0,size);
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? extends T> removeFrom, List<? extends T> c2) {
        for (T elem:c2)
            removeFrom.remove(elem);

    }

    //true если первый лист содержит все элементы второго
    public static <T> boolean containsAll(List<? extends T>  c1, List<? extends T>  c2) {
           return c1.containsAll(c2);
    }


    //true если первый лист содержит хотя-бы 1 второго
    public static <T> boolean containsAny(List<? extends T>  c1, List<? extends T>  c2) {
        for (T elem:c2)
           if (c1.contains(elem))
               return true;

        return false;
    }


    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
// Элементы сравнивать через Comparable.
// Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
  /*  public static <T> List range(List<T> list, T min, T max) {

    }*/


    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
// Элементы сравнивать через Comparable.
// Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List range(List<? extends T> list, T min, T max, Comparator comparator) {
        list.sort(comparator);
      return list.subList(list.indexOf(min),list.indexOf(max));
    }

}