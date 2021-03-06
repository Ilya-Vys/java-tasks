package com.example.lec07;

import java.math.BigInteger;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class ArrayFactorialsCounter {

    private final int[] arr;
   // private BigInteger[] result;
    private List<BigInteger> result;
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
        print();
    }

    private void sortArray(int[] arr) {
        this.sortedArr = Arrays.stream(arr).distinct().sorted().toArray();
    }

    private void multiCounting() {
        int floor = 1;
        List<FactorialCounter> tasks = new ArrayList<>();
        for (int i = 0; i < sortedArr.length; i++) {
            tasks.add(new FactorialCounter(floor, sortedArr[i]));
            floor = sortedArr[i] + 1;
        }
        tasks.forEach(ForkJoinTask::fork);
        result = tasks.stream()
                .map(ForkJoinTask::join)
                .collect(Collectors.toList());
       // result = pool.invoke(new ArrayDivider(sortedArr, 1));
    }

    private void multiple() {
        for (int i = 1; i < result.size(); i++) {
            result.set(i, result.get(i).multiply(result.get(i - 1)));
        }
    }

    private void print() {
        Map<Integer, BigInteger> map = new HashMap<>();
        for (int i = 0; i < sortedArr.length; i++) {
            map.put(sortedArr[i], result.get(i));
        }
        for (int value : arr) {
            System.out.println(String.format("Factorial of %d = %d", value, map.get(value)));
        }
    }

   /* private class ArrayDivider extends RecursiveTask<BigInteger[]>{

        private final int[] array;
        private int floor;

        public ArrayDivider(int[] array, int floor) {
            this.array = array;
            this.floor = floor;
        }

        @Override
        protected BigInteger[] compute() {
            int arrLength = array.length;
            BigInteger[] integers;
            if(arrLength == 1){
                System.out.println(
                        String.format("%s ArrayDivider started and gave to FactorialCounters pool from %d to %d",
                        Thread.currentThread().getName() , floor, array[0]));
                BigInteger bigInteger = pool.invoke(new FactorialCounter(floor, array[0]));
                integers = new BigInteger[]{bigInteger};
            }else {
                ArrayDivider[] dividers = new ArrayDivider[arrLength];
                integers = new BigInteger[arrLength];
                floor = 1;
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
    }*/
}
