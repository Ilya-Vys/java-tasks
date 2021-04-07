package com.example.lec04;

import com.example.lec04.exception.PetAlreadyExistException;

public class Test {



    public static void main(String[] args) throws PetAlreadyExistException {

        PetBaseFacade facade = new PetBaseFacade();

        facade.addPets();
        facade.getPets();
        facade.updatePet("Kitty", "Kittykat");

    }

}
