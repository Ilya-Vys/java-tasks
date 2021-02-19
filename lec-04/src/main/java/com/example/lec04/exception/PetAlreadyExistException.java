package com.example.lec04.exception;

public class PetAlreadyExistException extends Exception {

    public PetAlreadyExistException() {
        System.out.println("This pet is already exist");
    }
}
