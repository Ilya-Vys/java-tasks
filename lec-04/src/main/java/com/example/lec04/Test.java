package com.example.lec04;

import com.example.lec04.entities.Person;
import com.example.lec04.entities.Pet;
import com.example.lec04.entities.Sex;
import com.example.lec04.exception.PetAlreadyExistException;

public class Test {



    public static void main(String[] args) throws PetAlreadyExistException {
        PetBase base = new PetBase();
        base.addPet(new Pet("Murz", 5, new Person(Sex.MAN, "Bob", 30)));
        base.addPet(new Pet("Sharik", 15, new Person(Sex.MAN, "John", 35)));
        base.addPet(new Pet("Dereza", 35, new Person(Sex.MAN, "Jim", 33)));
        base.addPet(new Pet("Burenka", 100, new Person(Sex.WOMAN, "Kate", 20)));
        base.addPet(new Pet("Kesha", 1, new Person(Sex.WOMAN, "Anna", 70)));
        base.addPet(new Pet("Kenny", 2, new Person(Sex.WOMAN, "Anna", 50)));
        base.addPet(new Pet("Kitty", 2, new Person(Sex.WOMAN, "Anna", 50)));
        for (Pet s : base.pets) {
            System.out.println(s);
        }
        Pet pet = base.findByName("Kitty");
        pet.setName("Kittykat");
        base.updatePet(pet);
        System.out.println(base.findByName("Kittykat"));
    }
}
