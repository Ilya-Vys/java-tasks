package com.example.lec09.task2.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    private InputReader() {
    }

    public static List<String> readInput() throws IOException {
        List<String> input = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (!(line = reader.readLine()).equals("")) {
            input.add(line);
        }
        return input;
    }
}
