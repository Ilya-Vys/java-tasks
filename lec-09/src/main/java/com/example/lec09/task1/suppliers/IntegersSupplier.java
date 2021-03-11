package com.example.lec09.task1.suppliers;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegersSupplier extends AbstractSupplier<List<Integer>> {

    @Override
    public List<Integer> get() {
        return Stream.generate(new Random()::nextInt)
                .limit(super.getCount())
                .collect(Collectors.toList());
    }


}
