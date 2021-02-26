package com.example.lec06.task2;

import com.example.lec06.task2.suppliers.ParagraphSupplier;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextWriter {

    private final ParagraphSupplier supplier;

    public TextWriter(ParagraphSupplier supplier) {
        this.supplier = supplier;
    }


    public void getFiles(String path, int n, int size){
        for (int i = 0; i < n; i++) {
            try (FileWriter writer = new FileWriter(path + i + ".txt")) {
                writer.write(createText(size));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String createText(int size){
        return IntStream.range(0, size)
                .mapToObj(i -> supplier.get())
                .collect(Collectors.joining());
    }


}
