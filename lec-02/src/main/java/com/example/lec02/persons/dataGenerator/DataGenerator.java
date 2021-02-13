package com.example.lec02.persons.dataGenerator;

import com.example.lec02.persons.comparators.PersonComparator;
import com.example.lec02.persons.entities.Person;
import com.example.lec02.persons.exceptions.DuplicateException;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class DataGenerator {

    private final int count;
    private final AbstractSupplier<Person> supplier;
    private final PersonComparator comparator;

    public DataGenerator(int count, PersonComparator comparator) {
        this.count = count;
        supplier = new PersonSupplier();
        this.comparator = comparator;
    }

    public List<Person> generateDataWithTimeMeasure() throws DuplicateException {
        LocalTime start = LocalTime.now();
        List<Person> persons = generatePersons();
        if (hasDuplicates(persons, count)) {
            throw new DuplicateException();
        }
        //persons.forEach(System.out::println);
        LocalTime end = LocalTime.now();
        System.out.println("Execution time is: " + ChronoUnit.MILLIS.between(start, end));
        return persons;
    }

    private List<Person> generatePersons() {
        List<Person> result = supplier.getStream(count).sorted(comparator).
                collect(Collectors.toList());
        return result;
    }

    private boolean hasDuplicates(List<Person> persons, int count) {
        return persons.stream().distinct().count() < count;
    }
}
