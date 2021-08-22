package ru.job4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(4);

    public ThreadPool() {
        for (int i = 0; i < getPoolSize(); i++) {
            threads.add(new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }));
            }
        for (var thread : threads) {
            thread.start();
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

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        pool.work(() -> System.out.println(Thread.currentThread().getName()));
        pool.work(() -> System.out.println(Thread.currentThread().getName()));
        pool.work(() -> System.out.println(Thread.currentThread().getName()));
        pool.shutdown();
    }
}
