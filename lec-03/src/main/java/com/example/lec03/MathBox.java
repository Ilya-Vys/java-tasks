package com.example.lec03;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MathBox<T extends Number> extends ObjectBox{

    public MathBox(Number[] numbers) {
        super(new HashSet<>());
        for (Number number : numbers) {
            collection.add(number);
        }
    }

    public Double sum(){
        return collection.stream()
                .map(a -> ((Number) a).doubleValue())
                .reduce((a, b) -> a + b)
                .orElse(0d);
    }

    public Set<Double> splitter(double divider) {
        if (divider == 0) {
            throw new MyException();
        }
        return collection.stream()
                .map(a -> ((Number) a).doubleValue() / divider)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean addObject(Object o) {
        if (!(o instanceof Number)) {
            throw new MyException();
        } else {
            return super.addObject(o);
        }
    }
}
