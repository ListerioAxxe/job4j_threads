package ru.job4j;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName()));
        Thread sec = new Thread(
                () -> System.out.println(Thread.currentThread().getName()));
        first.start();
        sec.start();
        while (first.getState() != Thread.State.TERMINATED || sec.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
            System.out.println(sec.getState());
        }
        System.out.println("Работа завершена ...");
    }
}
