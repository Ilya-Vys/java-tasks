package com.example.lec06.task2.suppliers;

import com.example.lec06.task2.utils.RandomNumberSupplier;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordSupplier extends AbstractSupplier<String> {

    private final int maxLength;
    private final char[] alphabet;

    public WordSupplier() {
        this(15);
    }

    private WordSupplier(int maxLength) {
        this.maxLength = maxLength;
        this.alphabet = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
    }

    @Override
    public String get() {
        return IntStream.
                generate(() -> alphabet[RandomNumberSupplier.getNumber(alphabet.length)]).
                limit(RandomNumberSupplier.getNumber(maxLength) + 1).
                mapToObj(i -> (char) i).
                map(String::valueOf).
                collect(Collectors.joining());
    }
}
