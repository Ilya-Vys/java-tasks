package com.example.lec06.task1;


import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        String source = "lec-06/src/main/resources/destination1.txt";
        String destination = "sourcetext1.txt";
        TextSorter sorter = new TextSorter();
        List<String > readText = sorter.getText(source);
        List<String > sortedText = sorter.sortText(readText);
        sorter.writeText(sortedText, destination);

    }

}
