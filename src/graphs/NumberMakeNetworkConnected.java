package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b]
 * represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.
 * <p>
 * Given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them
 * between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in
 * order to make all the computers connected. If it's not possible, return -1.
 */
// We just need check if no of connected components less than (n-1) , then connected components -1.
public class NumberMakeNetworkConnected {
    public int makeConnected(int n, int[][] connections) {

        if (connections.length < n - 1) return -1;

        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int[] c : connections) {
            adjList.computeIfAbsent(c[0], k -> new ArrayList<>()).add(c[1]);
            adjList.computeIfAbsent(c[1], k -> new ArrayList<>()).add(c[0]);
        }

        Set<Integer> visited = new HashSet<>();

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                dfs(i, adjList, visited);
                count++;
            }
        }

        return count - 1; // Need (components-1) cables to connect components together, ideally all are connected together
    }

    private void dfs(int n, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
        if (visited.contains(n))
            return;

        visited.add(n);

        if (adjList.get(n) != null) {
            for (int next : adjList.get(n)) {
                dfs(next, adjList, visited);
            }
        }
    }
}
