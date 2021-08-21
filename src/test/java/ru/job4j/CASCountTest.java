package ru.job4j;

import junit.framework.TestCase;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenIncrementAndGet() throws InterruptedException {
       CASCount cas = new CASCount();
       Thread th1 = new Thread(() -> {
           for (int i = 0; i < 100; i++) {
               cas.increment();
           }
       });
        Thread th2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                cas.increment();
            }
        });
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        assertThat(cas.get(), is(100));
    }
}