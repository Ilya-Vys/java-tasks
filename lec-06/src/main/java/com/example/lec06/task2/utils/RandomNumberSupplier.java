package com.example.lec06.task2.utils;

import java.util.Random;

public class RandomNumberSupplier {

    public static int getNumber(int count){
        Random random = new Random();
        return random.nextInt(count);
    }
}
