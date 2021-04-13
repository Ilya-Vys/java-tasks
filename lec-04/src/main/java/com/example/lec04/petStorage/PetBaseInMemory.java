package com.example.lec04.petStorage;

import com.example.lec04.comparator.PetComparator;
import com.example.lec04.entities.Person;
import com.example.lec04.entities.Pet;
import com.example.lec04.exception.PetAlreadyExistException;


import java.io.IOException;
import java.util.*;

public class PetBaseInMemory implements Observed {

    private static volatile PetBaseInMemory INSTANCE;
    private final Set<Pet> pets;

    public static PetBaseInMemory getInstance() {
        if (INSTANCE == null) {
            synchronized (PetBaseInMemory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PetBaseInMemory();
                }
            }
        }
        return INSTANCE;
    }



    private PetBaseInMemory() {
        this.pets = new TreeSet<>(new PetComparator());
    }

    public void addPet(Pet pet) throws PetAlreadyExistException, IOException {
        if (pets.contains(pet)) {
            throw new PetAlreadyExistException();
        }else {
            pets.add(pet);
        }
    }

    public Pet findByName(String name) {
        return pets.stream().filter(pet -> pet.getName().equals(name)).findFirst().orElse(null);
    }

    public void updatePet(Pet pet) {
        pets.remove(pets.stream().filter(pet1 -> pet1.getId() == pet.getId()).findFirst().get());
    }

    public void updateOwner(Person person, int age){
        notifyObservers(person, age);
    }

    public Set<Pet> getPets() {
        return pets;
    }

    @Override
    public void notifyObservers(Person person, int age) {
        pets.forEach(pet -> pet.handleEvent(person, age));
    }
}
