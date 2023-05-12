package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1.
 * You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 * Return the number of pairs of different nodes that are unreachable from each other.
 * Input: n = 3, edges = [[0,1],[0,2],[1,2]]
 * Output: 0
 * Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.
 *
 * Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
 * Output: 14
 * Explanation: There are 14 pairs of nodes that are unreachable from each other:
 * [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
 * Therefore, we return 14.
 */
public class CountUnreachableNodesUndirectedGraph {

    /*
        There are n-1 no of total edges so no of connections are (n-1)+(n-2)+...+1
        No of set of vertices in a group of nodes without repetitions is n*(n-1)/2
        Idea is to use DFS and find no of nodes that could potentially be connected.
        Then for each set of connected components in the graph find the connections present i.e (size)(size-1)/2
        Reduce from the total.
     */
    public long countPairs(int n, int[][] edges) {
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList[u].add(v);
            adjList[v].add(u);
        }

        boolean[] visited = new boolean[n];
        // not typecasting can cause overflow
        long total = ((long)n*(long)(n-1))/2;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                long size = dfs(i, adjList, visited);
                total-=(size*(size-1))/2;
            }
        }

        return total;
    }

    private long dfs(int u, List<Integer>[] adjList, boolean[] visited) {
        visited[u] = true;
        long size = 1l;
        for (int v : adjList[u]) {
            if (!visited[v]) {
                size += dfs(v, adjList, visited);
            }
        }
        return size;
    }
}
