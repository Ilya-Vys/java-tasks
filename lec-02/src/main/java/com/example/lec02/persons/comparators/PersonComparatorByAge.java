package com.example.lec02.persons.comparators;

import com.example.lec02.persons.entities.Person;

public class PersonComparatorByAge implements PersonComparator{
    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
