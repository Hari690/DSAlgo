package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
 * You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
 * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
 * Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
 *
 * Example 1:
 * Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
 *
 * Input: colors = "a", edges = [[0,0]]
 * Output: -1
 * Explanation: There is a cycle from 0 to 0.
 */
public class LargestColorValueInDirectedGraph {
    public int largestPathValue(String colors, int[][] edges) {
        if(edges.length == 0)
            return 1;

        List<Integer>[] adj = new List[colors.length()];
        for(int[] edge: edges) {
            if(adj[edge[0]] == null) {
                adj[edge[0]] = new ArrayList<>();
            }

            adj[edge[0]].add(edge[1]);
        }

        boolean[] visited = new boolean[colors.length()];

        // for each node store the value per colour
        int[][] dp = new int[colors.length()][26];

        int max = 0;
        for(int i = 0; i < colors.length(); i++) {
            if(!visited[i]) {
                if(checkCycleDFS(colors, visited, dp, new boolean[colors.length()], adj, i)) {
                    return -1;
                }

                // dp[i][c] = max number of times we see color c
                // in any given path starting from node i
                for(int c = 0; c < 26; c++) {
                    max = Math.max(dp[i][c], max);
                }
            }
        }

        return max;
    }

    // return true if cycle detected.
    private boolean checkCycleDFS(String colors, boolean[] visited, int[][] dp, boolean[] inChain, List<Integer>[] adj, int node) {
        if(inChain[node]) {
            return true;
        }

        if(visited[node]) {
            return false;
        }

        inChain[node] = true;
        visited[node] = true;

        if(adj[node] != null) {
            for(int nbr: adj[node]) {
                if(checkCycleDFS(colors, visited, dp, inChain, adj, nbr)) {
                    return true;
                }

                for(int c = 0; c < 26; c++) {
                    dp[node][c] = Math.max(dp[node][c], dp[nbr][c]);
                }
            }
        }

        dp[node][colors.charAt(node) - 'a']++;
        inChain[node] = false;

        return false;
    }
}
