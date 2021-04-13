package com.example.lec04.petStorage;

import com.example.lec04.entities.Person;
import com.example.lec04.entities.Pet;
import com.example.lec04.exception.PetAlreadyExistException;

import java.io.IOException;

public class PetBaseFacade {

    private final PetBase base;

    public PetBaseFacade(PetBase base) {
        this.base = base;
    }

    public void addPet(Pet pet) throws PetAlreadyExistException, IOException {
        base.addPet(pet);
    }

    public void getPets() throws IOException {
        base.getPets().forEach(System.out::println);
    }

    public void updatePet(String name, String wantedName) throws IOException {
        Pet pet = base.findByName(name);
        base.updatePet(pet);
        pet.setName(wantedName);
        base.addPet(pet);
        System.out.println(base.findByName(wantedName));
    }

    public Pet findByName(String name) throws IOException {
        return base.findByName(name);
    }

    public void updateOwnerAge(Person person, int age) throws IOException {
        Person p = new Person(person.getName(), person.getAge(), person.getSex());
        base.updateOwner(p, age);

    }
}
