package com.homework6.BeanUtils;

import com.homework3.my_linked_list.Person;

public class MainBeanUtils {
    public static void main(String[] args) {
        Person person1= new Person("Bob",35);
        Person person2 =  new Person("Mashs", 33);

        BeanUtils.assign(person1,person2);

    }
}
