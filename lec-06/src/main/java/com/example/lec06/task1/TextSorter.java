package com.example.lec06.task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextSorter {

    private String source;
    private String destination;

    public TextSorter(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public List<String> getText() throws FileNotFoundException {
        URL file = this.getClass().getClassLoader().getResource(source);
        File file1 = new File(file.getFile());
        try (Stream<String> fileReader = Files
                .lines(Paths.get(file1.getCanonicalPath()))) {
            return fileReader.collect(Collectors.toList());
        } catch (IOException | NullPointerException e) {
            throw new FileNotFoundException("file: " + source + " is not found");
        }
    }

    public List<String> sortText(List<String> text) {
        return splitTextToWords(text).
                stream().
                distinct().
                sorted().
                        collect(Collectors.toList());
    }

    private List<String> splitTextToWords(List<String> list) {
        List<String> words = new ArrayList<>();
        list.stream().map(String::toLowerCase).
                        map(s -> s.replaceAll("\\p{P}", "")).
                forEach(s -> words.addAll(Arrays.asList(s.split(" "))));
        return words;
    }

    public void writeText(List<String> words) {
        try (FileWriter writer = new FileWriter(destination)) {
            for (String word : words) {
                writer.write(word + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
