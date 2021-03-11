package com.example.lec09.task1.suppliers;

import java.util.function.Supplier;

public abstract class AbstractSupplier<T> implements Supplier<T> {

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
