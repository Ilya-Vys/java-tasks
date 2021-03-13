package com.example.lec09.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader{

    private final String path;

    CustomClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            return defineClass("com.example.lec09.task2.SomeClass", bytes, 0 , bytes.length);
            //  return defineClass(name, bytes, 0 , bytes.length);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }

}
