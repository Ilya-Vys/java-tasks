package com.example.lec06.task2;

import com.example.lec06.task2.suppliers.ParagraphSupplier;
import com.example.lec06.task2.suppliers.SentenceSupplier;
import com.example.lec06.task2.suppliers.WordSupplier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Test {

    public static void main(String[] args) throws IOException {
        String source = "lec-06/src/main/resources/dictionary.txt";
        String[] words = Files.readAllLines(new File(source).toPath()).toArray(String[]::new);
        WordSupplier wordSupplier = new WordSupplier(words);
        SentenceSupplier sentenceSupplier = new SentenceSupplier(15, wordSupplier);
        ParagraphSupplier paragraphSupplier = new ParagraphSupplier(20, sentenceSupplier);
        TextWriter writer = new TextWriter(paragraphSupplier);
        writer.getFiles("lec-06/src/main/resources/task-2-generated-Text-", 3, 5);
    }
}
