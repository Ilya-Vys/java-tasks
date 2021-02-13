package com.example.lec02.persons.utils;

import java.util.Random;

public class RandomNumberSupplier {

    public static int generateNumbers(int count){
        Random random = new Random();
        return random.nextInt(count);
    }
}
