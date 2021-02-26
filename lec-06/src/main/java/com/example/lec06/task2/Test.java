package com.example.lec06.task2;

import com.example.lec06.task2.suppliers.ParagraphSupplier;

public class Test {

    public static void main(String[] args) {
       TextWriter writer = new TextWriter(new ParagraphSupplier());
       writer.getFiles("lec-06/src/main/resources/task-2-generated-Text-", 3, 5);
    }
}
