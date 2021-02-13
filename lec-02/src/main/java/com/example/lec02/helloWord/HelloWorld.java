package com.example.lec02.helloWord;

public class HelloWorld {

    public static void main(String[] args) {

        String argument = modelingArrayIOBException(args);

        modelingNPE(argument);

        System.out.println(modelingMyException(argument));

    }

    private static String modelingArrayIOBException(String[] args){
        String result;
        try{
            result = args[0];
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("We have caught ArrayIndexOutOfBoundsException");
            result = null;
        }
        return result;
    }

    private static void modelingNPE(String s){
        try{
            s.split("");
        }catch (NullPointerException e){
            System.out.println("We have caught NPE");
        }
    }

    private static String modelingMyException(String s)  {
        try{
            if(s == "Hello World"){
                return "It's Hello World!";
            }else {
                throw new MyException();
            }
        }catch (MyException e){
            return "It's not Hello World!";
        }

    }
}
