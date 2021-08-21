package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int countFull;

    public SimpleBlockingQueue(int countFull) {
        this.countFull = countFull;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == countFull) {
            wait();
        }
            notifyAll();
        queue.offer(value);
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
            notifyAll();
       return queue.poll();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }
}
