package com.example.lec02.persons.dataGenerator;

import com.example.lec02.persons.entities.Sex;
import com.example.lec02.persons.utils.RandomNumberSupplier;


public class SexSupplier extends AbstractSupplier<Sex> {
    @Override
    public Sex get() {
        return Sex.values()[RandomNumberSupplier.generateNumbers(Sex.values().length)];
    }
}
