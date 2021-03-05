package com.example.lec07;


import java.util.concurrent.ForkJoinPool;

public class Test {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ArrayFactorialsCounter counter = new ArrayFactorialsCounter(pool,new int[]{10000, 20000, 30000, 40000});
        counter.countAndPrint();

    }

}
