package com.example.lec04;

import com.example.lec04.entities.Person;
import com.example.lec04.entities.Pet;
import com.example.lec04.entities.Sex;
import com.example.lec04.exception.PetAlreadyExistException;

public class PetBaseFacade {

    private PetBase base = PetBase.getInstance();

    public void addPets() throws PetAlreadyExistException {
        base.addPet(new Pet.Builder(new Person("Bob", 20, Sex.MAN), "Bobik").build());
        base.addPet(new Pet.Builder(new Person("Bob", 30, Sex.MAN), "Tuzik").ageYears(2).build());
        base.addPet(new Pet.Builder(new Person("Bob", 40, Sex.MAN), "Sharik").build());
        base.addPet(new Pet.Builder(new Person("Bob", 50, Sex.WOMAN), "Frosya").ageMonths(3).build());
        base.addPet(new Pet.Builder(new Person("Bob", 60, Sex.MAN), "Kesha").build());
        base.addPet(new Pet.Builder(new Person("Bob", 25, Sex.WOMAN), "Murka").build());
        base.addPet(new Pet.Builder(new Person("Bob", 35, Sex.MAN), "Kitty").build());
    }

    public void getPets(){
        base.getPets().forEach(System.out::println);
    }

    public void updatePet(String name, String wantedName){
        Pet pet = base.findByName(name);
        pet.setName(wantedName);
        base.updatePet(pet);
        System.out.println(base.findByName(wantedName));
    }
}
