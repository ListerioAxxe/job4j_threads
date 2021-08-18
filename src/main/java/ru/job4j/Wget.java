package ru.job4j;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    for (int i = 0; i <= 100; i++) {
                        System.out.print("\rLoading: " + i + "%");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
        thread.start();

    }
}
