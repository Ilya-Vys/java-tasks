package com.example.lec04.entities;

public class Person implements Comparable{

    private final String name;
    private final int age;
    private final Sex sex;

    public Person(Sex sex, String name, int age) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Person person = (Person)o;
        int nameResult = this.name.compareTo(person.name);
        if(nameResult != 0)
            return nameResult;
        return Integer.compare(this.getAge(), person.getAge());
    }
}
