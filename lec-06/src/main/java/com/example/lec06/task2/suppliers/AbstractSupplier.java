package com.example.lec06.task2.suppliers;

import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class AbstractSupplier<T> implements Supplier<T> {

    public Stream<T> getStream(int length) {
        return IntStream.range(0, length)
                .mapToObj(i -> get());
    }
}
