package com.example.lec04.petStorage;

import com.example.lec04.entities.Person;
import com.example.lec04.entities.Pet;

import java.io.IOException;
import java.util.Set;

public interface PetBase {

    void addPet(Pet pet) throws IOException;

    Pet findByName(String name) throws IOException;

    void updatePet(Pet pet) throws IOException;

    void updateOwner(Person person, int age) throws IOException;

    Set<Pet> getPets() throws IOException;
}
