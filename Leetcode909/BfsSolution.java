package Leetcode909;

import java.util.*;

class BfsSolution {
    public static void main(String[] args) {
//        int[][] testData = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
//        int[][] testData = {{-1, -1}, {-1, 3}};
//        int[][] testData = {{-1,-1,-1},{-1,9,8},{-1,8,9}};
        int[][] testData = {{-1,-1,-1,13,123,-1,-1,-1,-1,37,-1,-1},{-1,-1,-1,-1,-1,-1,123,-1,-1,-1,-1,-1},{123,-1,-1,-1,79,70,-1,-1,17,-1,-1,103},{-1,-1,120,-1,101,-1,2,72,-1,-1,-1,-1},{-1,71,77,-1,-1,-1,-1,35,-1,-1,-1,-1},{-1,-1,98,-1,-1,-1,-1,-1,-1,99,-1,-1},{83,-1,108,27,-1,-1,113,-1,-1,-1,79,-1},{28,-1,-1,-1,57,14,-1,48,-1,-1,-1,-1},{-1,-1,-1,-1,16,115,-1,46,-1,-1,-1,-1},{-1,-1,4,-1,-1,-1,-1,-1,-1,-1,-1,-1},{94,-1,116,-1,-1,-1,39,100,-1,-1,16,-1},{-1,94,-1,-1,53,-1,-1,-1,-1,-1,-1,-1}};
        long start = System.currentTimeMillis();
        int result = BfsSolution.snakesAndLadders(testData);
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("result is: " + result + " with time of " + elapsedTime);
    }
    public static int snakesAndLadders(int[][] board) {
        HashMap<Integer, Integer> locationToLevel = new HashMap<>();
        Map<Integer, ArrayList<Integer>> levelToLocation = new HashMap<>();
        ArrayList<Integer> firstLocationList = new ArrayList<>();
        firstLocationList.add(1);
        Set<Integer> locationSet = new HashSet<>();
        locationSet.add(1);

        locationToLevel.put(1, 1);
        levelToLocation.put(1, firstLocationList);
        for (int i = 1; i <= board.length * board.length; i++) {
            ArrayList<Integer> locationList = levelToLocation.get(i);
            levelToLocation.put(i + 1, new ArrayList<>());
            for (int j = 0; j < locationList.size(); j++) {
                int location = locationList.get(j);
                if (location + 6 >= board.length * board.length) {
                    return i;
                }
                for (int k = 1; k <= 6; k++) {
                    int newStartLocation = location + k;
                    int[] boardLocation = convertNumberToLocation(newStartLocation, board.length);
                    int newStartLocationValue = board[boardLocation[0]][boardLocation[1]];
                    if (newStartLocationValue == board.length * board.length) {
                        return i;
                    }
                    if (newStartLocationValue != -1) {
                        newStartLocation = newStartLocationValue;
                    }
                    if (locationSet.contains(newStartLocation)){
                        continue;
                    }
                    locationSet.add(newStartLocation);
                    levelToLocation.get(i + 1).add(newStartLocation);
                }
            }
        }
        return -1;
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
