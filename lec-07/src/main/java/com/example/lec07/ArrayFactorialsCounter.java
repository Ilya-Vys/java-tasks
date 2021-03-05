package com.example.lec07;

import java.math.BigInteger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ArrayFactorialsCounter {

    private final int[] arr;
    private BigInteger[] result;
    private int[] sortedArr;
    private final ForkJoinPool pool;

    public ArrayFactorialsCounter(ForkJoinPool pool, int[] arr) {
        this.pool = pool;
        this.arr = arr;
    }

    public void countAndPrint() {
        sortArray(arr);
        multiCounting();
        multiple();
        print(arr);
    }

    private void sortArray(int[] arr) {
        this.sortedArr = Arrays.stream(arr).distinct().sorted().toArray();
    }

    private void multiCounting() {
        result = pool.invoke(new ArrayDivider(sortedArr, 1));
    }

    private void multiple() {
        for (int i = 1; i < result.length; i++) {
            result[i] = result[i].multiply(result[i - 1]);
        }
    }

    private void print(int[] arr) {
        Map<Integer, BigInteger> map = new HashMap<>();
        for (int i = 0; i < sortedArr.length; i++) {
            map.put(sortedArr[i], result[i]);
        }
        for (int value : arr) {
            System.out.println("Factorial of " + value + " = " + map.get(value));
        }
    }

    private class ArrayDivider extends RecursiveTask<BigInteger[]>{

        private BigInteger[] integers;
        private int[] array;
        private int floor;

        public ArrayDivider(int[] array, int floor) {
            this.array = array;
            this.floor = floor;
        }

        @Override
        protected BigInteger[] compute() {
            int arrLength = array.length;
            if(arrLength == 1){
                System.out.println(Thread.currentThread().getName() + " ArrayDivider started and gave to FactorialCounters " +
                        "pool from " + floor + " to " + array[0]);
                BigInteger bigInteger = pool.invoke(new FactorialCounter(floor, array[0]));
                integers = new BigInteger[]{bigInteger};
            }else {
                ArrayDivider[] dividers = new ArrayDivider[arrLength];
                integers = new BigInteger[arrLength];
                int floor = 1;
                for (int i = 0; i < array.length; i++) {
                    dividers[i] = new ArrayDivider(new int[]{array[i]}, floor);
                    floor = array[i];
                    floor++;
                }
                Arrays.stream(dividers).forEach(ForkJoinTask::fork);
                for (int i = 0; i < integers.length; i++) {
                    integers[i] = dividers[i].join()[0];
                }
            }
            return integers;
        }
    }
}
