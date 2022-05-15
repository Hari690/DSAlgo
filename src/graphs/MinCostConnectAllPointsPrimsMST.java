package graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 */
public class MinCostConnectAllPointsPrimsMST {
    class Pair {
        int node;
        int distance;
        Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
    public int minCostConnectPoints(int[][] points) {
        // create adj list of points where a point points to points after it.
        // also contains distance between one point to another.
        Map<Integer, List<Pair>> adjList = new HashMap<>();

        for(int i=0;i<points.length;i++) {
            adjList.put(i, new ArrayList<>());
        }

        for(int i=0;i<points.length;i++) {
            for(int j=i+1;j<points.length;j++) {
                int distance = Math.abs(points[j][0]-points[i][0]) + Math.abs(points[j][1]-points[i][1]);
                List<Pair> neighbours = adjList.get(i);
                neighbours.add(new Pair(j, distance));
                neighbours = adjList.get(j);
                neighbours.add(new Pair(i, distance));
            }
        }

        // Prims algorithm to find Minimum Spanning Tree
        // Need a set and pq.
        // We iterate until set has all nodes.
        // Get the nearest node from a given node and add to pq. Now keep retrieving from the pq
        // for the nearest node from any node and add to set.
        // Keep track of total distance.
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distance));
        int total=0;
        pq.add(new Pair(0,0));
        while(visited.size()<points.length) {
            Pair node = pq.poll();
            if(!visited.contains(node.node)) {
                visited.add(node.node);
                for(Pair neighbour : adjList.get(node.node)) {
                    pq.add(neighbour);
                }
                total+=node.distance;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        MinCostConnectAllPointsPrimsMST minCostConnectAllPointsPrimsMST = new MinCostConnectAllPointsPrimsMST();
        int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        minCostConnectAllPointsPrimsMST.minCostConnectPoints(points);
    }
}
