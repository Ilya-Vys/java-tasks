package com.example.lec06.task2.utils;

import java.util.Random;

public class RandomNumberSupplier {

    private static final Random random = new Random();

    public static int getNumber(int count){
        return random.nextInt(count);
    }
}
