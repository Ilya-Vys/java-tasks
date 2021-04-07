package com.example.lec04;

import com.example.lec04.entities.Person;
import com.example.lec04.entities.Pet;
import com.example.lec04.entities.Sex;
import com.example.lec04.exception.PetAlreadyExistException;

public class Test {



    public static void main(String[] args) throws PetAlreadyExistException {

        PetBaseFacade facade = new PetBaseFacade();

        facade.addPet(new Pet.Builder(new Person("Bob", 20, Sex.MAN), "Bobik").build());
        facade.addPet(new Pet.Builder(new Person("Bob", 30, Sex.MAN), "Tuzik").ageYears(2).build());
        facade.addPet(new Pet.Builder(new Person("Bob", 40, Sex.MAN), "Sharik").build());
        facade.addPet(new Pet.Builder(new Person("Bob", 50, Sex.WOMAN), "Frosya").ageMonths(3).build());
        facade.addPet(new Pet.Builder(new Person("Bob", 60, Sex.MAN), "Kesha").build());
        facade.addPet(new Pet.Builder(new Person("Bob", 25, Sex.WOMAN), "Murka").build());
        facade.addPet(new Pet.Builder(new Person("Bob", 35, Sex.MAN), "Kitty").build());

        facade.getPets();

        facade.updatePet("Kitty", "Kittykat");

    }

}
