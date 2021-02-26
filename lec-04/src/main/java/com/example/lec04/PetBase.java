package com.example.lec04;

import com.example.lec04.comparator.PetComparator;
import com.example.lec04.entities.Pet;
import com.example.lec04.exception.PetAlreadyExistException;


import java.util.*;

public class PetBase {

    Set<Pet> pets;

    public PetBase() {
        this.pets = new TreeSet<>(new PetComparator());
    }

    public void addPet(Pet pet) throws PetAlreadyExistException {
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
        pets.add(pet);
    }
}
