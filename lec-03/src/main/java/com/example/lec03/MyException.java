package com.example.lec03;

public class MyException extends ArithmeticException{

    public MyException() {
        System.out.println("Exception threw");
    }
}
