package ru.job4j;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(4);

    public ThreadPool() {
        for (int i = 0; i < getPoolSize(); i++) {
            threads.add(new Thread(() -> {
                System.out.println("Поток " + Thread.currentThread().getName() + " стартовал...");
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Поток " + Thread.currentThread().getName() + " работает");
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " прерван");
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
    }

    public void work(Runnable job) throws InterruptedException {
       tasks.offer(job);
    }

    public void shutdown() {
        for (var thread : threads) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private int getPoolSize() {
        return Runtime.getRuntime().availableProcessors();
    }
}
