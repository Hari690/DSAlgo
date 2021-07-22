package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b]
 * represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.
 *
 * Given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them
 * between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in
 * order to make all the computers connected. If it's not possible, return -1.
 */
// We just need check if no of connected components less than (n-1) , then connected components -1.
public class MakeNetworkConnected {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1; // To connect all nodes need at least n-1 edges
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] c : connections) {
            graph[c[0]].add(c[1]);
            graph[c[1]].add(c[0]);
        }
        int components = 0;
        boolean[] visited = new boolean[n];
        for (int v = 0; v < n; v++) components += dfs(v, graph, visited);
        return components - 1; // Need (components-1) cables to connect components together
    }
    int dfs(int u, List<Integer>[] graph, boolean[] visited) {
        if (visited[u]) return 0;
        visited[u] = true;
        for (int v : graph[u]) dfs(v, graph, visited);
        return 1;
    }
}
