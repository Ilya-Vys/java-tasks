package com.example.lec04.petStorage;

import com.example.lec04.comparator.PetComparator;
import com.example.lec04.entities.Person;
import com.example.lec04.entities.Pet;
import com.example.lec04.petStorage.Observed;
import com.example.lec04.petStorage.PetBase;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;

public class PetBaseInFile implements PetBase, Observed {

    private final String fileName;
    private final ObjectMapper mapper;

    public PetBaseInFile(String fileName) {
        this.fileName = fileName;
        this.mapper = new ObjectMapper();
    }

    @Override
    public void notifyObservers(Person person, int age) throws IOException {
        Set<Pet> pets = getPets();
        pets.forEach(pet -> pet.handleEvent(person, age));
        pets.stream()
                .filter(pet -> pet.getOwner().getName().equals(person.getName()))
                .forEach(pet -> {
                    try {
                        addPet(pet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void addPet(Pet pet) throws IOException {
        mapper.writeValue(new File(fileName + "/" + pet.getName() + ".json"), pet);
    }

    @Override
    public Pet findByName(String name) throws IOException {
        Path petPath = Files.walk(Path.of(fileName + "/"))
                .filter(path -> path.toString().endsWith(name + ".json"))
                .findAny()
                .get();

        return mapper.readValue(petPath.toFile(), Pet.class);
    }

    @Override
    public void updatePet(Pet pet) throws IOException {
      deletePet(pet);
    }

    @Override
    public void updateOwner(Person person, int age) throws IOException {
        notifyObservers(person, age);
    }

    @Override
    public Set<Pet> getPets() throws IOException {
        Set<Pet> pets = new TreeSet<>(new PetComparator());
        Files.walk(Path.of(fileName + "/"))
                .filter(path -> !path.toFile().isDirectory())
                .forEach(path -> {
                    try {
                        pets.add(mapper.readValue(path.toFile(), Pet.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        return pets;
    }

    private void deletePet(Pet pet) throws IOException {
        Files.delete(Path.of(fileName + "/" + pet.getName() + ".json"));

    }
}
