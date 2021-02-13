package com.example.lec02.persons;

import com.example.lec02.persons.comparators.PersonComparatorByAge;
import com.example.lec02.persons.comparators.PersonComparatorByAllFields;
import com.example.lec02.persons.comparators.PersonComparatorByName;
import com.example.lec02.persons.comparators.PersonComparatorBySex;
import com.example.lec02.persons.dataGenerator.DataGenerator;
import com.example.lec02.persons.exceptions.DuplicateException;



public class Test {
    public static void main(String[] args) throws DuplicateException {
        new DataGenerator(10000, new PersonComparatorByAllFields()).
                generateDataWithTimeMeasure();
        new DataGenerator(10000, new PersonComparatorBySex()).
                generateDataWithTimeMeasure();
        new DataGenerator(10000, new PersonComparatorByAge()).
                generateDataWithTimeMeasure();
        new DataGenerator(10000, new PersonComparatorByName()).
                generateDataWithTimeMeasure();






    }
}
