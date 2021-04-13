package com.example.lec04;

import com.example.lec04.entities.Person;
import com.example.lec04.entities.Pet;
import com.example.lec04.entities.Sex;
import com.example.lec04.exception.PetAlreadyExistException;
import com.example.lec04.petStorage.PetBaseFacade;
import com.example.lec04.petStorage.PetBaseInFile;
import com.example.lec04.petStorage.PetBaseInMemory;

import java.io.IOException;

public class Test {



    public static void main(String[] args) throws PetAlreadyExistException, IOException {
        PetBaseInMemory petBaseInMemory = PetBaseInMemory.getInstance();
        PetBaseInFile petBaseInFile = new PetBaseInFile("lec-04/src/main/resources/pets");


        PetBaseFacade facade = new PetBaseFacade(petBaseInFile);

        facade.addPet(new Pet.Builder(new Person("Bob", 20, Sex.MAN), "Bobik").build());
        facade.addPet(new Pet.Builder(new Person("John", 30, Sex.MAN), "Tuzik").ageYears(2).build());
        facade.addPet(new Pet.Builder(new Person("Mary", 40, Sex.MAN), "Sharik").build());
        facade.addPet(new Pet.Builder(new Person("Rob", 50, Sex.WOMAN), "Frosya").ageMonths(3).build());
        facade.addPet(new Pet.Builder(new Person("Jacob", 60, Sex.MAN), "Kesha").build());
        facade.addPet(new Pet.Builder(new Person("Jason", 25, Sex.WOMAN), "Murka").build());
        facade.addPet(new Pet.Builder(new Person("Bob", 20, Sex.MAN), "Kitty").build());
        facade.addPet(new Pet.Builder(new Person("Bob", 20, Sex.MAN), "SnowBall").build());

        facade.getPets();
        System.out.println("=================================================================");

        facade.updatePet("Kitty", "Kittykat");
        System.out.println("=================================================================");

        Person person = facade.findByName("Bobik").getOwner();
        System.out.println(person);
        facade.updateOwnerAge(person, 21);
        facade.getPets();

    }

}
