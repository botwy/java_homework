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
        link_list.add(new Person("Grisha",23));

        Collection<Employee> c = new ArrayList<Employee>();
        c.add(new Employee("D",5));
        c.add(new Employee("S",9));
        c.add(new Employee("M",8));
       // c.add(new Person("Z",11));

        link_list.addAll(c);
      // link_list.copy(c);
       // link_list.add(1,new Person("Mike", 25));
     //   System.out.println(link_list.get(2));
       // link_list.remove(2);
        Iterator<Person> iterator = link_list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }


}