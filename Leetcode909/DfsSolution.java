package Leetcode909;

import java.util.HashSet;
import java.util.Set;

public class DfsSolution {

    public static void main(String[] args) {
//        int[][] testData = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
//        int[][] testData = {{-1, -1}, {-1, 3}};
//        int[][] testData = {{-1,-1,-1},{-1,9,8},{-1,8,9}};
        int[][] testData = {{-1,-1,-1,13,123,-1,-1,-1,-1,37,-1,-1},{-1,-1,-1,-1,-1,-1,123,-1,-1,-1,-1,-1},{123,-1,-1,-1,79,70,-1,-1,17,-1,-1,103},{-1,-1,120,-1,101,-1,2,72,-1,-1,-1,-1},{-1,71,77,-1,-1,-1,-1,35,-1,-1,-1,-1},{-1,-1,98,-1,-1,-1,-1,-1,-1,99,-1,-1},{83,-1,108,27,-1,-1,113,-1,-1,-1,79,-1},{28,-1,-1,-1,57,14,-1,48,-1,-1,-1,-1},{-1,-1,-1,-1,16,115,-1,46,-1,-1,-1,-1},{-1,-1,4,-1,-1,-1,-1,-1,-1,-1,-1,-1},{94,-1,116,-1,-1,-1,39,100,-1,-1,16,-1},{-1,94,-1,-1,53,-1,-1,-1,-1,-1,-1,-1}};
        long start = System.currentTimeMillis();
        int result = DfsSolution.snakesAndLadders(testData);
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("result is: " + result + " with time of " + elapsedTime);
    }
    public static int snakesAndLadders(int[][] board) {
        int size = board.length;
        // int[] record  = new int[size * size];
        // for (int i = 0; i < size * size; i++) {
        //     record[i] = -1;
        // }
        int[] shortestRoute = new int[1];
        shortestRoute[0] = size * size;
        int startLocation = 1;
        Set<Integer> knownLocation = new HashSet<>();
        knownLocation.add(1);
        int[] count = new int[1];
        iterate(shortestRoute, board, 0, startLocation, knownLocation, count);
        if (shortestRoute[0] == size * size) {
            System.out.println("Number of function call: " + count[0]);
            return -1;
        }
        System.out.println("Number of function call: " + count[0]);
        return shortestRoute[0];
    }

    private static void iterate(int[] shortestRoute, int[][] board, int level, int startLocation, Set<Integer> knownLocation, int[] count) {
        count[0] = count[0] + 1;
        if (level >= shortestRoute[0] - 1 || shortestRoute[0] == 2 || startLocation + 6 >= board.length * board.length) {
            shortestRoute[0] = shortestRoute[0] > (level + 1) ? (level + 1) : shortestRoute[0];
            return;
        }
        for (int i = 1; i <= 6; i++) {
//            if (shortestRoute[0] <= level + 1) {
//                return;
//            }
            int newStartLocation = startLocation + i;
            int[] boardLocation = convertNumberToLocation(newStartLocation, board.length);
            int newStartLocationValue = board[boardLocation[0]][boardLocation[1]];
            if (newStartLocationValue != -1) {
                newStartLocation = newStartLocationValue;
            }
            if (knownLocation.contains(newStartLocation)) {
                continue;
            }
            if (newStartLocation == board.length * board.length) {
                shortestRoute[0] = shortestRoute[0] > (level + 1) ? (level + 1) : shortestRoute[0];
                return;
            }
            knownLocation.add(newStartLocation);
            iterate(shortestRoute, board, level + 1, newStartLocation, knownLocation, count);
            knownLocation.remove(newStartLocation);
        }
        return;
    }

    private static int[] convertNumberToLocation(int number, int n) {
        int x = number / n;
        int y = number % n;
        int[] res = new int[2];
        if (y == 0) {
            res[0] = n - x;
            if (x % 2 == 0) {
                res[1] = 0;
            } else {
                res[1] = n - 1;
            }
        } else {
            res[0] = n - 1 - x;
            if (x % 2 == 0) {
                res[1] = y - 1;
            } else {
                res[1] = n - y;
            }
        }
        return res;
    }
}