package com.example.lec02.numbersGenerator;

import java.util.Arrays;
import java.util.Random;

public class NumberGenerator {
    int[] array;

    public NumberGenerator(int n) {
        if (n < 0){
            throw new IllegalArgumentException();
        }
        array = new int[n];
    }

    public void generateNumbers(){
        Random random = new Random();
        Arrays.stream(array).map(num -> random.nextInt(100))
                .filter(this::isPerfectSquare)
                .forEach(System.out::println);
    }

    private boolean isPerfectSquare(int num){
        long tst = (long)(Math.sqrt(num));
        return tst*tst == num;
    }


}
