package com.example.lec07;


import java.math.BigInteger;

import java.util.concurrent.Callable;
import java.util.stream.IntStream;

public class FactorialCounter implements Callable<BigInteger> {

    private final int start;
    private final int end;

    public FactorialCounter(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public BigInteger call() {
        return countFactorial(start, end);
    }

    private BigInteger countFactorial(int start, int end) {
        System.out.println(Thread.currentThread().getName() + " started and counting factorial from " + start + " to " + end);
        if (start == end || start == end - 1) return BigInteger.valueOf(end);
        return IntStream.rangeClosed(start, end)
                .parallel()
                .mapToObj(String::valueOf)
                .map(BigInteger::new)
                .reduce(BigInteger::multiply)
                .get();


    }

}
