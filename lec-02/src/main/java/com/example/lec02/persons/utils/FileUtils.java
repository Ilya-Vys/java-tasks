package com.example.lec02.persons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {

    public static List<String> parseTextStreamFromResource(String resource) {
        try (InputStream is = FileUtils.class.getResourceAsStream(resource)) {
            return parseTextStream(is);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to parse resource '" +
                    resource + "' due " + e.getLocalizedMessage(), e);
        }
    }

    public static List<String> parseTextStream(InputStream is) {
        try (Scanner scanner = new Scanner(is)) {
            List<String> result = new ArrayList<>();
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }
            return result;
        }
    }
}
