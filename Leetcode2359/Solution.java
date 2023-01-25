package Leetcode2359;

import Leetcode909.DfsSolution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
//        int[] edges = {5,3,1,0,2,4,5};
//        int node1 = 3;
//        int node2 = 2;

        int[] edges = {-1,7,15,15,-1,4,16,2,16,7,11,6,10,4,9,1,14,-1};
        int node1 = 1;
        int node2 = 6;

        long start = System.currentTimeMillis();
        int result = Solution.closestMeetingNode(edges, node1, node2);
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("result is: " + result + " with time of " + elapsedTime);
    }

    public static int closestMeetingNode(int[] edges, int node1, int node2) {
        Map<Integer, Integer> nodeToDistanceForNode1 = new HashMap<>();
        Map<Integer, ArrayList<Integer>> distanceToNodeListForNode1 = new HashMap<>();
        Map<Integer, Integer> nodeToDistanceForNode2 = new HashMap<>();
        Map<Integer, ArrayList<Integer>> distanceToNodeListForNode2 = new HashMap<>();
        fillTwoMaps(edges, node1, nodeToDistanceForNode1, distanceToNodeListForNode1);
        fillTwoMaps(edges, node2, nodeToDistanceForNode2, distanceToNodeListForNode2);
        // if (nodeToDistanceForNode1.containsKey(node2) || nodeToDistanceForNode2.containsKey(node1)) {
        //     // Add check to rule out the case that two nodes are in the same circle.
        //     if (nodeToDistanceForNode1.containsKey(node2) && nodeToDistanceForNode2.containsKey(node1)) {
        //         return nodeToDistanceForNode1.get(node2) > nodeToDistanceForNode2.get(node1) ? node1 : node2;
        //     }
        //     if (nodeToDistanceForNode1.containsKey(node2)) {
        //         return node2;
        //     }
        //     if (nodeToDistanceForNode2.containsKey(node1)) {
        //         return node1;
        //     }
        // }

        int maximumDistance = edges.length;
        int targetNode = edges.length;
        for (int i = 0; i < edges.length; i++) {
            // Todo, check if i is largerOrEqual than maximumDistance.
            if (i > maximumDistance || !distanceToNodeListForNode1.containsKey(i)) {
                continue;
            }
            ArrayList<Integer> nodeListForNode1 = distanceToNodeListForNode1.get(i);
            for (int node : nodeListForNode1) {
                if (!nodeToDistanceForNode2.containsKey(node)) {
                    continue;
                }
                int distanceToNode2 = nodeToDistanceForNode2.get(node);
                int maximumDistanceTemp = distanceToNode2 >= i ? distanceToNode2 : i;
                if (maximumDistanceTemp < maximumDistance) {
                    maximumDistance = maximumDistanceTemp;
                    targetNode = node;
                } else if (maximumDistanceTemp == maximumDistance) {
                    targetNode = targetNode > node ? node : targetNode;
                }
            }
        }
        if (maximumDistance == edges.length) {
            return -1;
        }
        return targetNode;
    }

    private static void fillTwoMaps(int[] edges, int node, Map<Integer, Integer> nodeToDistance, Map<Integer, ArrayList<Integer>> distanceToNodeList) {
        nodeToDistance.put(node, 0);
        ArrayList<Integer> nodeList = new ArrayList<>();
        nodeList.add(node);
        distanceToNodeList.put(0, nodeList);
        boolean notEnd = true;
        int m = node;
        int distance = 0;
        while (notEnd) {
            // Add key check to rule out circle
            if (edges[m] != -1 && !nodeToDistance.containsKey(edges[m])) {
                distance++;
                nodeToDistance.put(edges[m], distance);
                ArrayList<Integer> nodeListTemp = new ArrayList<>();
                nodeListTemp.add(edges[m]);
                distanceToNodeList.put(distance, nodeListTemp);
                m = edges[m];
            } else {
                notEnd = false;
            }
        }
    }
}