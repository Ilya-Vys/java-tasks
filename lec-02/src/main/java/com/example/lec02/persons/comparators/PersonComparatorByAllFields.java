package com.example.lec02.persons.comparators;

import com.example.lec02.persons.entities.Person;

public class PersonComparatorByAllFields implements PersonComparator{
    @Override
    public int compare(Person o1, Person o2) {
        int sexResult = o1.getSex().compareTo(o2.getSex());
        if(sexResult != 0)
            return sexResult;
        int ageResult =Integer.compare(o1.getAge(), o2.getAge());
        if(ageResult != 0)
            return  -ageResult;
        return o1.getName().compareTo(o2.getName());

    }
}
