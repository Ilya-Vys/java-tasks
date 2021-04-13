package com.example.lec04.petStorage;

import com.example.lec04.entities.Person;

import java.io.IOException;

public interface Observed {

    void notifyObservers(Person person, int age) throws IOException;
}
