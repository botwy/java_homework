package com.homework2;

public class Main {

    public static void main(String[] args) {
	    Person person1=new Person(true,"Dima");
        Person person2=new Person(false,"Masha");
        Person person3=new Person(false,"Natasha");
        person1.marry(person3);
        System.out.println(person1.marry(person2));
        System.out.println(person1.marry(person2));
        System.out.println(person2.marry(person3));
    }
}
