package com.example.lec02.persons.dataGenerator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomOptionSupplier<T> extends AbstractSupplier<T> {

    private final List<T> options;

    public RandomOptionSupplier(List<T> options) {
        this.options = options;
    }

    @Override
    public T get() {
        return options.get(ThreadLocalRandom.current().nextInt(options.size()));
    }
}
