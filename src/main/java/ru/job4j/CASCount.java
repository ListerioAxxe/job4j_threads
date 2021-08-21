package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        do {
            count.set(get() + 1);
        }
        while (count.compareAndSet(get(), get() + 1));
    }

    public int get() {
        return count.get();
    }
}