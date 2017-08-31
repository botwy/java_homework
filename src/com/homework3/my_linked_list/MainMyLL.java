package com.homework3.my_linked_list;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MainMyLL {
    public static void main(String[] args) {

        MyLinkedList<Person> link_list =  new MyLinkedList<Person>();
        link_list.add(new Person("Dima",35));
        link_list.add(new Person("Sasha",29));
        link_list.add(new Person("Masha",18));

        Collection<Person> c = new ArrayList<>();
        c.add(new Person("D",5));
        c.add(new Person("S",9));
        c.add(new Person("M",8));
       // c.add(new Person("Z",11));

       // link_list.addAll(c);
        link_list.copy(c);
        //link_list.add(3,new Person("Mike", 25));
        //System.out.println(link_list.get(6));
        //link_list.remove(6);
        Iterator<Person> iterator = link_list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }


}