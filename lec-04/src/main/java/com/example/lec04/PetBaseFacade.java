package com.example.lec04;

import com.example.lec04.entities.Pet;
import com.example.lec04.exception.PetAlreadyExistException;

public class PetBaseFacade {

    private PetBase base = PetBase.getInstance();

    public void addPet(Pet pet) throws PetAlreadyExistException {
        base.addPet(pet);
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
