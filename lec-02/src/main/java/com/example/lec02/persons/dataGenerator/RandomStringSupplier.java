package com.example.lec02.persons.dataGenerator;

import com.example.lec02.persons.utils.FileUtils;

import java.util.List;

public class RandomStringSupplier extends RandomOptionSupplier<String>{

    public RandomStringSupplier(List<String> options) {
        super(options);
    }

    public static RandomStringSupplier fromResourceFile(String resource) {
        return new RandomStringSupplier(FileUtils.parseTextStreamFromResource(resource));
    }
}
