package ru.job4j.userstore;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolExample extends RecursiveTask<Integer> {

    private final Integer[] array;
    private final int from;
    private final int to;
    private final int value;

    public ForkJoinPoolExample(Integer[] array, int from, int to, int value) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.value = value;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 10) {
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
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int find(Integer[] array, int value) {
        ForkJoinPool fjp = new ForkJoinPool();
        return fjp.invoke(new ForkJoinPoolExample(array, 0, array.length - 1, value));
    }
}
