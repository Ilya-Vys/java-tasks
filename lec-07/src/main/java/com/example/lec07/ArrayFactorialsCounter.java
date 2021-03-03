package com.example.lec07;

import java.math.BigInteger;
import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayFactorialsCounter {

    private ExecutorService executor;
    private BigInteger[] result;

    public ArrayFactorialsCounter() {

    }

    public void countAndPrint(int[] arr)  {
        int[] sortedArr = sortArray(arr);
            multiCounting(sortedArr);
            multiple();
            print(arr, sortedArr);

    }

    private int[] sortArray(int[] arr) {
        return Arrays.stream(arr).distinct().sorted().toArray();
    }

    private void multiCounting(int[] array) {
        result = new BigInteger[array.length];
        executor = Executors.newFixedThreadPool(array.length);
        int floor = 1;
        for (int i = 0; i < array.length; i++) {
            try {
                result[i] = executor.submit(new FactorialCounter(floor, array[i])).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            floor = array[i];
            floor++;
        }
        executor.shutdown();
    }

    private void multiple(){
        for (int i = 1; i < result.length; i++) {
            result[i] = result[i].multiply(result[i - 1]);
        }
    }

    private void print(int[] arr, int[] sortedArr){
        for (int value : arr) {
            int index = ArrayUtils.indexOf(sortedArr, value);
            System.out.println("Factorial of " + value + " = " + result[index]);
        }
    }
}
