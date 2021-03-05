package com.example.lec07;

import java.math.BigInteger;

import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;


public class FactorialCounter extends RecursiveTask<BigInteger> {

    private final int start;
    private final int end;

    public FactorialCounter(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected BigInteger compute() {
        if ((end - start)  <= 5000) {
            return countFactorial();
        } else {
            int mid = (start + end) / 2;
            FactorialCounter lowerRange = new FactorialCounter(start, mid);
            FactorialCounter higherRange = new FactorialCounter(mid + 1, end);
            lowerRange.fork();
            higherRange.fork();
            return lowerRange.join().multiply(higherRange.join());
        }
    }

    private BigInteger countFactorial() {
        System.out.println(Thread.currentThread().getName() +
                " FactorialCounter started and counting factorial from " + start + " to " + end);
        if (start == end || start == end - 1) return BigInteger.valueOf(end);
        return IntStream.rangeClosed(start, end)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger::multiply)
                .get();
    }
}
