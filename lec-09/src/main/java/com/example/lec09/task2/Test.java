package com.example.lec09.task2;


import com.example.lec09.task2.utils.InputReader;

import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String className = "SomeClass";
        InterfaceImplCodeBuilder builder = new InterfaceImplCodeBuilder(
                "lec-09/src/main/java/com/example/lec09/task2/Worker.java"
                , className);
        List<String> newClassCode = builder.getNewClassCode();
        newClassCode.forEach(System.out::println);
        List<String> inputCode = InputReader.readInput();
        ClassBuilder classBuilder = new ClassBuilder(
                "lec-09/src/main/java/com/example/lec09/task2/"
                , className
        , inputCode);
        classBuilder.buildInstance().doWork();
    }

}
