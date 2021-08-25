package ru.job4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("hello");
        ArrayList<?> list = new ArrayList<>();
        ArrayList<String> list1 = (ArrayList<String>) list.clone();
        ArrayList<?> list2 = new ArrayList<>();
        System.out.println(list1);
    }
}
