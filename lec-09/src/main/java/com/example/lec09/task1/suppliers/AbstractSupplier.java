package com.example.lec09.task1.suppliers;

import java.util.function.Supplier;

public abstract class AbstractSupplier<U> implements Supplier<U> {

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
