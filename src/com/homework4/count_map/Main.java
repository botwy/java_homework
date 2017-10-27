package com.homework4.count_map;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //пример использования 
       CountMap<Integer> map = new CountMapImpl<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);
/*
        int count = map.getCout(5); // 2
        int count = map.getCout(6); // 1
        int count = map.getCout(10); // 3*/

List<Number> list = CollectionUtils.newArrayList();
CollectionUtils.add(list,new Integer(1));
CollectionUtils.add(list,new Double(2.2));

    }
}
