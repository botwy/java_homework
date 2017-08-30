package com.homework3.my_linked_list;


import java.util.Iterator;

public class MainMyLL {
    public static void main(String[] args) {

        MyLinkedList<Person> link_list =  new MyLinkedList<Person>();
        link_list.add(new Person("Dima",35));
        link_list.add(new Person("Sasha",29));
        link_list.add(new Person("Masha",18));

        //System.out.println(link_list.get(0));
link_list.remove(0);
        Iterator<Person> iterator = link_list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }


}