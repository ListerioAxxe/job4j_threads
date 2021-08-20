package ru.job4j.userstore;

import org.junit.Test;
import ru.job4j.SimpleBlockingQueue;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlock1  {

    @Test
    public void whenProducerAndConsumer() throws InterruptedException {
        SimpleBlockingQueue<Integer> sq = new SimpleBlockingQueue<>(3);
        CopyOnWriteArrayList<Integer> test = new CopyOnWriteArrayList<>();
        Thread producer = new Thread(() -> {
            try {
                sq.offer(1);
                sq.offer(2);
                sq.offer(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer = new Thread(() -> {
            while (!sq.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    test.add(sq.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        consumer.interrupt();
        assertThat(test, is(List.of(1,2,3)));
    }
}