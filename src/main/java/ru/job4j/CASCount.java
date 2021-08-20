package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
            Integer current = get();
            Integer next = get() + 1;
            if (count.compareAndSet(current, next)) {
                count.set(next);
            }
    }

    public int get() {
        return count.get();
    }
}