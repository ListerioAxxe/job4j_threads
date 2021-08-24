package ru.job4j.userstore;


import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ForkJoinPoolExampleTest  {
    @Test
    public void foundElement() {
        Integer[] input = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        assertEquals(3, ForkJoinPoolExample.find(input, 4));
    }

}