package com.example.lec06.task1;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextSorter {

    public List<String> getText(String source) throws IOException {
        return Files.readAllLines(new File(source).toPath());
    }

    public List<String> sortText(List<String> text) {
        return splitTextToWords(text).stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private List<String> splitTextToWords(List<String> list) {
        List<String> words = new ArrayList<>();
        list.stream()
                .map(String::toLowerCase)
                .map(s -> s.replaceAll("\\p{P}", ""))
                .forEach(s -> words.addAll(Arrays.asList(s.split(" "))));
        return words;
    }

    public void writeText(List<String> words, String destination) {
        try (FileWriter writer = new FileWriter(destination)) {
            for (String word : words) {
                writer.write(word + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
