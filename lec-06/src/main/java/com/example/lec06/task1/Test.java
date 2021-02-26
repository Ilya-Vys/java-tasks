package com.example.lec06.task1;


import java.io.FileNotFoundException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        TextSorter sorter = new TextSorter("sourcetext1.txt", "lec-06/src/main/resources/destination1.txt");
        List<String > readText = sorter.getText();
        List<String > sortedText = sorter.sortText(readText);
        sorter.writeText(sortedText);

    }

}
