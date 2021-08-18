package ru.job4j;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Wgeet implements Runnable {
    private final String url;
    private final int speed;

    public Wgeet(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (var in = new BufferedInputStream(new URL(url).openStream())) {
            var fos = new FileOutputStream("temp.txt");
            byte[] dataBuffer = new byte[1024];
            long start = System.nanoTime();
            int byteRead;
            while ((byteRead = in.read(dataBuffer, 0, 1024)) != 0) {
                long end = System.nanoTime();
                long duration = end - start;
                fos.write(dataBuffer, 0, byteRead);
                if (duration < speed) {
                    Thread.sleep(speed - duration);
                }
                start = System.nanoTime();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wgeet(url, speed));
        wget.start();
        wget.join();
    }
}
