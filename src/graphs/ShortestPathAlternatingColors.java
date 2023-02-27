package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.
 * You are given two arrays redEdges and blueEdges where:
 * redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
 * blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
 * Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.
 *
 * Example 1:
 *
 * Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
 * Output: [0,1,-1]
 * Example 2:
 *
 * Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
 * Output: [0,1,-1]
 */
public class ShortestPathAlternatingColors {

    /*
        2 BFS or keep two colors internally, maintain different adjList and continue
     */
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Map<Integer, List<Integer>> redAdjList = new HashMap<>();
        Map<Integer, List<Integer>> blueAdjList = new HashMap<>();

        for(int[] arr : redEdges)
            redAdjList.computeIfAbsent(arr[0], k -> new ArrayList<>()).add(arr[1]);
        for(int[] arr : blueEdges)
            blueAdjList.computeIfAbsent(arr[0], k -> new ArrayList<>()).add(arr[1]);

        Queue<Node> queue = new LinkedList<>();
        Set<Integer> blueVisited = new HashSet<>();
        Set<Integer> redVisited = new HashSet<>();

        queue.add(new Node(0, 0, null));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if(result[node.node]==-1)
                result[node.node] = node.length;

            visitNeighbour(redAdjList, queue, redVisited, node, "BLUE", "RED");
            visitNeighbour(blueAdjList, queue, blueVisited, node, "RED", "BLUE");
        }

        return result;
    }

    private void visitNeighbour(Map<Integer, List<Integer>> adjList, Queue<Node> queue, Set<Integer> visited, Node node, String currColor, String nextColor) {
        if(node.color==null || node.color.equals(currColor)) {
            if (adjList.get(node.node)!=null) {
                for(int next : adjList.get(node.node)) {
                    if(!visited.contains(next)) {
                        visited.add(next);
                        queue.add(new Node(next, node.length+1, nextColor));
                    }
                }
            }
        }
    }

    static class Node {
        int node;
        int length;
        String color;

        public Node(int node, int length, String color) {
            this.node = node;
            this.length = length;
            this.color = color;
        }
    }
}
