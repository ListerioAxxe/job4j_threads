package ru.job4j;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName()));
        Thread sec = new Thread(
                () -> System.out.println(Thread.currentThread().getName()));
        first.start();
        sec.start();
        first.join();
        sec.join();
        System.out.println("Работа завершена ...");
    }
}
