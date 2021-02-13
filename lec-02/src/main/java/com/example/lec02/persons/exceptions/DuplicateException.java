package com.example.lec02.persons.exceptions;

public class DuplicateException extends Exception{
    public DuplicateException() {
        System.out.println("Duplicates detected!");
    }
}
