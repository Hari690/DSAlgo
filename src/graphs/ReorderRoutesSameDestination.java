package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities
 * (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too
 * narrow.
 *
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 *
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 *
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
 *
 * It's guaranteed that each city can reach city 0 after reorder.
 */

/*
    The idea here is to do a BFS and check for outbound edges since there can only be one path between
    2 cities and then count them.
    In terms of doing BFS start from final destination(0) and checking for all neighbours
    it requires an adjacency list containing both source, destination and destination, source
    so we can reach all nodes.
 */
public class ReorderRoutesSameDestination {
    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<String> roads = new HashSet<>();
        for (int[] connection: connections) {
            int a = connection[0];
            int b = connection[1];
            graph.putIfAbsent(a, new ArrayList<>());
            graph.get(a).add(b);
            graph.putIfAbsent(b, new ArrayList<>());
            graph.get(b).add(a);
            roads.add(a + "," + b);
        }
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int result = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int neighbor: graph.get(curr)) {
                if (!seen.contains(neighbor)) {
                    if (roads.contains(curr + "," + neighbor)) {
                        result++;
                    }
                    seen.add(neighbor);
                    q.add(neighbor);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ReorderRoutesSameDestination reorderRoutesSameDestination = new ReorderRoutesSameDestination();

        reorderRoutesSameDestination.minReorder(6, new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}});
    }
}
