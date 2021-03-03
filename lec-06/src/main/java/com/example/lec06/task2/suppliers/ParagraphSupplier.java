package com.example.lec06.task2.suppliers;

import com.example.lec06.task2.utils.RandomNumberSupplier;

import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ParagraphSupplier extends AbstractSupplier<String> {

    private final int maxLength;
    private final SentenceSupplier supplier;

    public ParagraphSupplier(int maxLength, SentenceSupplier supplier) {
        this.maxLength = maxLength;
        this.supplier = supplier;
    }

    @Override
    public String get() {
        List<String> list = combineAllLists();
        return "   " + joinStrings(list) + "\n";
    }

    private List<String> combineAllLists() {
        return supplier.
                getStream(RandomNumberSupplier.getNumber(maxLength) + 1).
                flatMap(Collection::stream).
                collect(Collectors.toList());
    }

    private String joinStrings(List<String> list) {
        StringJoiner joiner = new StringJoiner(" ");
        int rowLength = 0;
        for (String s : list) {
            if (rowLength + s.length() > 100) {
                s = "\n" + s;
                rowLength = s.length();
            } else {
                rowLength += s.length();
            }
            joiner.add(s);
        }
        return joiner.toString();
    }
}
