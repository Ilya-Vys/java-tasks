package com.example.lec02.persons.dataGenerator;

import com.example.lec02.persons.entities.Person;
import com.example.lec02.persons.entities.Sex;
import com.example.lec02.persons.utils.RandomNumberSupplier;


public class PersonSupplier extends AbstractSupplier<Person> {

    private final RandomStringSupplier maleNameSupplier;
    private final RandomStringSupplier femaleNameSupplier;
    private final SexSupplier sexSupplier;

    public PersonSupplier() {
        this(RandomStringSupplier.fromResourceFile("/data/male-names.txt"),
                RandomStringSupplier.fromResourceFile("/data/female-names.txt"),
                new SexSupplier());
    }

    public PersonSupplier(RandomStringSupplier maleNameSupplier,
                          RandomStringSupplier femaleNameSupplier,
                          SexSupplier sexSupplier) {
        this.maleNameSupplier = maleNameSupplier;
        this.femaleNameSupplier = femaleNameSupplier;
        this.sexSupplier = sexSupplier;
    }

    @Override
    public Person get() {
        Sex sex = sexSupplier.get();
        return new Person(sex,
                sex.name().equals("MAN") ? maleNameSupplier.get() : femaleNameSupplier.get(),
                RandomNumberSupplier.generateNumbers(60)+ 18);

    }
}
