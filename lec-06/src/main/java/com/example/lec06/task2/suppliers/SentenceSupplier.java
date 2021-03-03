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
        injectCommas(list);
        list.set(list.size() - 1 , injectLastPunctuation(list.get(list.size() - 1)));
        return list;
    }

    private List<String> getWords(){
        return supplier.
                getStream( RandomNumberSupplier.getNumber(maxLength) +1).
                collect(Collectors.toList());
    }

    private String makeFirstWord(String word){
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    private List<String> injectCommas(List<String> list){
        for (int i = 0; i < list.size() - 1; i++) {
            if (RandomNumberSupplier.getNumber(4) == 0)
                list.set(i, list.get(i) + ",");
        }
        return list;
    }

    private String injectLastPunctuation(String word){
        int random = RandomNumberSupplier.getNumber(3);
       return word + (random == 0 ? "!" : random == 1 ? "?" : ".");
    }


}
