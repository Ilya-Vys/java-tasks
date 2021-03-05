package com.example.lec06.task2.suppliers;

import com.example.lec06.task2.utils.RandomNumberSupplier;


public class WordSupplier extends AbstractSupplier<String> {

    private final String[] words;

    public WordSupplier(String[] words) {
        this.words = words;
    }

    public String get() {
        return words[RandomNumberSupplier.getNumber(words.length)];


    }
}
