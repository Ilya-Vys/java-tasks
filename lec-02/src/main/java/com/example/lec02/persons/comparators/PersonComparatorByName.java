package com.example.lec02.persons.comparators;

import com.example.lec02.persons.entities.Person;

public class PersonComparatorByName implements PersonComparator{
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
