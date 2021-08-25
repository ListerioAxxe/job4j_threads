package ru.job4j;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

public class TestClass extends Thread {
    public static void main(String[] args) {
        byte a  = 127;
        a++;
        System.out.println(a);
     }
    }
