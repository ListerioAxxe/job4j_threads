package ru.job4j.userstore;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolExample extends RecursiveTask<Integer> {

    private final Integer[] array;
    private final Integer from;
    private final Integer to;
    private final Integer value;

    public ForkJoinPoolExample(Integer[] array, Integer from, Integer to, Integer value) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.value = value;
    }

    @Override
    public Integer compute() {
        if (to - from < 10) {
            return find();
        }
        int mid = (from + to) / 2;
        ForkJoinPoolExample leftFind = new ForkJoinPoolExample(array, from, mid, value);
        ForkJoinPoolExample rightFind = new ForkJoinPoolExample(array, mid + 1, to, value);
        leftFind.fork();
        rightFind.fork();
        int leftInd = leftFind.join();
        int rightInd = rightFind.join();
        return leftInd != -1 ? leftInd : rightInd;
    }

    private int find() {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static int find(Integer[] array, Integer value) {
        ForkJoinPool fjp = new ForkJoinPool();
        return fjp.invoke(new ForkJoinPoolExample(array, 0, array.length - 1, value));
    }
}
