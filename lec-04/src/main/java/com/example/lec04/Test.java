package com.example.lec04;

import com.example.lec04.entities.*;
import com.example.lec04.exception.PetAlreadyExistException;

public class Test {



    public static void main(String[] args) throws PetAlreadyExistException {
        PetBase base = PetBase.getInstance();

        base.addPet(new Pet.Builder(new Person("Bob", 20, Sex.MAN), "Bobik").build());
        base.addPet(new Pet.Builder(new Person("Bob", 30, Sex.MAN), "Tuzik").ageYears(2).build());
        base.addPet(new Pet.Builder(new Person("Bob", 40, Sex.MAN), "Sharik").build());
        base.addPet(new Pet.Builder(new Person("Bob", 50, Sex.WOMAN), "Frosya").ageMonths(3).build());
        base.addPet(new Pet.Builder(new Person("Bob", 60, Sex.MAN), "Kesha").build());
        base.addPet(new Pet.Builder(new Person("Bob", 25, Sex.WOMAN), "Murka").build());
        base.addPet(new Pet.Builder(new Person("Bob", 35, Sex.MAN), "Kitty").build());

        base.getPets().forEach(System.out::println);

        Pet pet = base.findByName("Kitty");
        pet.setName("Kittykat");
        base.updatePet(pet);
        System.out.println(base.findByName("Kittykat"));


    }

}
