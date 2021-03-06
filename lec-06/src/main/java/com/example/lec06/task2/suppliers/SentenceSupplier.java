package com.example.lec06.task2.suppliers;

import com.example.lec06.task2.utils.RandomNumberSupplier;

import java.util.List;
import java.util.stream.Collectors;

public class SentenceSupplier extends AbstractSupplier<List<String>> {

    private final int maxLength;
    private final WordSupplier supplier;

    public SentenceSupplier(int maxLength, WordSupplier supplier) {
        this.maxLength = maxLength;
        this.supplier = supplier;
    }

    @Override
    public List<String> get() {
        List<String> list = getWords();
        list.set(0, makeFirstWord(list.get(0)));
        list.set(list.size() - 1 , injectLastPunctuation(list.get(list.size() - 1)));
        return list;
    }

    private List<String> getWords(){
        return supplier.getStream( RandomNumberSupplier.getNumber(maxLength) +1)
                .map(String::toLowerCase)
                .map(this::injectCommas)
                .collect(Collectors.toList());
    }

    private String makeFirstWord(String word){
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    private String injectCommas(String word){
        if (RandomNumberSupplier.getNumber(4) == 0){
            word += ",";
        }
        return word;
    }

    private String injectLastPunctuation(String word){
        char[] chars = new char[]{'!', '?', '.'};
        int random = RandomNumberSupplier.getNumber(3);
        return word.replace(",", "") + chars[random];
    }


}
