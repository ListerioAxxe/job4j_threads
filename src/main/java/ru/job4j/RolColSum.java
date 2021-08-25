package ru.job4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] rslt = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
           rslt[i] = getSum(matrix, i);
        }
         return rslt;
    }

    private static Sums getSum(int[][] matrix, int index) {
        int rowSums = 0;
        int colSums = 0;
        for (int i = 0; i < matrix.length; i++) {
            rowSums += matrix[index][i];
            colSums += matrix[i][index];
        }
        return new Sums(rowSums, colSums);
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] rslt = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getTask(matrix, i));
        }
        for (var el : futures.keySet()) {
            rslt[el] = futures.get(el).get();
        }
        return rslt;
    }

    private static CompletableFuture<Sums> getTask(int[][] matrix, int index) {
         return CompletableFuture.supplyAsync(() -> {
             return getSum(matrix, index);
         });
    }
}
