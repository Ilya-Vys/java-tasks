package com.example.lec09.task2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader{

    private final String packagePath;

    public CustomClassLoader(String packagePath) {
        this.packagePath = packagePath;
    }

    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = new byte[0];
        try {
            b = loadClassFromFile(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.defineClass(packagePath, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName) throws IOException {
        Path path = Files.walk(Paths.get(fileName + ".class")).findFirst().get();
        return Files.readAllBytes(path);
    }

}
