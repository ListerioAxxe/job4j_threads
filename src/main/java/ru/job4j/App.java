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
        ArrayList<?> list = new ArrayList<>();
        ArrayList<String> list1 = (ArrayList<String>) list.clone();
        System.out.println(list1);
    }
}
