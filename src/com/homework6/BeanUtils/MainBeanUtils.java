package com.homework6.BeanUtils;

import com.homework3.my_linked_list.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainBeanUtils {
    public static void main(String[] args) {
        Person person1= new Person("Bob",35);
        Person person2 =  new Person("Masha", 33);

        BeanUtils.assign(person1,person2);

        System.out.println(person1);

        Method[] arr_meth = person1.getClass().getDeclaredMethods();
        for (int i = 0; i < arr_meth.length ; i++) {
            if (arr_meth[i].getName().substring(0,3).equals("get"))
                System.out.println(arr_meth[i]);
        }
        Field[] arr_field = person1.getClass().getFields();
        for (int i = 0; i <arr_field.length ; i++) {
        if (Modifier.isFinal(arr_field[i].getModifiers())
                && arr_field[i].getType().equals(String.class))
            try {
                if (arr_field[i].getName().equals(arr_field[i].get(person1)))
                    System.out.println("имя final поля "+arr_field[i].getName() + " равно значению");
                else
                System.out.println("имя final поля "+arr_field[i].getName() + " НЕ равно значению");

        } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


    }
}
