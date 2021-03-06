package com.example.lec06.task1;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextSorter {

    public List<String> getText(String source) throws IOException {
        return Files.readAllLines(new File(source).toPath());
    }

    public List<String> sortTextToWordsAndSort(List<String> text) {
        return text.stream()
                .map(string -> string.split("\\p{P}"))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public void writeText(List<String> words, String destination) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(destination))) {
            words.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
