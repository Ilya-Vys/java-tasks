package com.example.lec02.persons.comparators;

import com.example.lec02.persons.entities.Person;

public class PersonComparatorBySex implements PersonComparator{


    @Override
    public int compare(Person o1, Person o2) {
        return o1.getSex().compareTo(o2.getSex());
    }
}
