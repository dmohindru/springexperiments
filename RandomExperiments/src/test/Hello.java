package test;

import java.util.Arrays;
import java.util.List;

public class Hello {
    public static void main(String[] args) {
        List<String> listOne = Arrays.asList("A", "E", "F", "G");
        List<String> listTwo = Arrays.asList("B", "F", "I");
        System.out.println(listOne.containsAll(listTwo));
        System.out.println(listTwo.containsAll(listOne));


        System.out.println("Hello World");
    }
}
