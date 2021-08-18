package ru.job4j;

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
        for (int i = 0; i < process.length; i++) {
            if (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r load: " + process[i]);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
