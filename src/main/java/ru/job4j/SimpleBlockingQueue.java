package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int countFull;

    public SimpleBlockingQueue(int countFull) {
        this.countFull = countFull;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (this.queue.size() == this.countFull) {
            wait();
        }
        if (this.queue.isEmpty()) {
            notifyAll();
        }
        queue.offer(value);
    }

    public synchronized T poll() throws InterruptedException {
        while (this.queue.isEmpty()) {
            wait();
        }
        if (this.queue.size() == this.countFull) {
            notifyAll();
        }
       return this.queue.poll();
    }

    public boolean isEmpty() {
        return this.queue.size() == 0;
    }
}
