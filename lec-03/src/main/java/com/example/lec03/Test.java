package com.example.lec03;

public class Test {

    public static void main(String[] args) {
        Integer[] arr = {22, 1262, 234, 111, 81, 987, 3};
        MathBox box = new  MathBox(arr);
        System.out.println(box.sum());
        System.out.println(box.splitter(100));
        System.out.println(box.addObject(100));
        System.out.println(box.addObject("100"));
    }
}
