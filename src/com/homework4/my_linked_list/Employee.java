package com.homework4.my_linked_list;

public class Employee extends Person {
    private float salary;
    public Employee(String name, long age) {
        super(name, age);
        salary=30000f;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getSalary() {

        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

