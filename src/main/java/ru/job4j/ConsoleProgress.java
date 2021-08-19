package ru.job4j;

import java.util.HashMap;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ConsoleProgress());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        String[] process = {"\\", "|", "/", "-", "\\", "\\", "|", "/", "-", "\\"};
            while (!Thread.currentThread().isInterrupted()) {
                for (var el : process) {
                    System.out.print("\r load: " + el);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
