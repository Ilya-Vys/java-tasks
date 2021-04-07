package com.example.lec04.comparator;

import com.example.lec04.entities.Pet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PetComparator implements Comparator<Pet> {

    @Override
    public int compare(Pet o1, Pet o2) {

        int ownerNameResult = o1.getOwner().getName().compareTo(o2.getOwner().getName());
        if (ownerNameResult != 0)
            return ownerNameResult;
        int ownerAgeResult = Integer.compare(o1.getOwner().getAge(), o2.getOwner().getAge());
        if (ownerAgeResult != 0)
            return ownerAgeResult;
        int petNameResult = o1.getName().compareTo(o2.getName());
        if (petNameResult != 0)
            return petNameResult;
        return -Integer.compare(o1.getWeight(), o2.getWeight());

    }


}
