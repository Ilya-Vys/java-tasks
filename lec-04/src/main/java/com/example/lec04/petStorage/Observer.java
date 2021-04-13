package com.example.lec04.petStorage;

import com.example.lec04.entities.Person;

public interface Observer {

    void handleEvent(Person person, int age);
}
