package com.example.lec07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class Test {

    public static void main(String[] args) throws Exception {
        ArrayFactorialsCounter counter = new ArrayFactorialsCounter();
        counter.countAndPrint(new int[]{5, 3, 4, 7});
        counter.countAndPrint(new int[]{15, 30, 40, 70});
        counter.countAndPrint(new int[]{150, 300, 400, 700});


    }

}
