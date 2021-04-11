package com.example.lec04;

import com.example.lec04.entities.Person;

public interface Observed {

    void notifyObservers(Person person, int age);
}
