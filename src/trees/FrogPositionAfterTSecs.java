package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an undirected tree consisting of n vertices numbered from 1 to n. A frog starts jumping from vertex 1. In one second, the frog jumps from its current vertex to another unvisited vertex if they are directly connected. The frog can not jump back to a visited vertex. In case the frog can jump to several vertices, it jumps randomly to one of them with the same probability. Otherwise, when the frog can not jump to any unvisited vertex, it jumps forever on the same vertex.
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.
 * Return the probability that after t seconds the frog is on the vertex target. Answers within 10-5 of the actual answer will be accepted.
 */
public class FrogPositionAfterTSecs {

    public double frogPosition(int n, int[][] edges, int t, int target) {
        if (n == 1) return 1.0;
        LinkedList<Integer> adjListArray[] = new LinkedList[n + 1];
        for(int i = 0; i <= n ; i++) adjListArray[i] = new LinkedList<>();
        for (int[] edge : edges) {
            // undirected graph so add both parent to child and child to parent and then use visited array.
            adjListArray[edge[0]].add(edge[1]);
            adjListArray[edge[1]].add(edge[0]);
        }
        return dfs(1, t, target, new boolean[n + 1], adjListArray);
    }

    private double dfs(int node, int t, int target, boolean[] visited, LinkedList<Integer> adjListArray[]) {
        if (node != 1 && adjListArray[node].size() == 1 || t == 0) {
            if (node == target)
                return 1;
            else return 0;
        }
        visited[node] = true;
        double res = 0.0;
        for (int child : adjListArray[node]) {
            if (visited[child]) continue; // skip visited children
            res+= dfs(child, t - 1, target, visited, adjListArray);
            if(res!=0)
                break;
        }

        // since we hold connections from children to parent and parent to children,
        // we need to exclude parent for all cases except root node
        return res * 1.0 / (adjListArray[node].size() - ((node==1)?0:1));
    }

    public double frogPositionBFS(int n, int[][] edges, int t, int target) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0] - 1].add(e[1] - 1);
            graph[e[1] - 1].add(e[0] - 1);
        }
        boolean[] visited = new boolean[n]; visited[0] = true;
        double[] prob = new double[n]; prob[0] = 1f;
        Queue<Integer> q = new LinkedList<>(); q.offer(0);
        while (!q.isEmpty() && t-- > 0) {
            for (int size = q.size(); size > 0; size--) {
                int u = q.poll(), nextVerticesCount = 0;
                for (int v : graph[u]) if (!visited[v]) nextVerticesCount++;
                for (int v : graph[u]) {
                    if (!visited[v]) {
                        visited[v] = true;
                        q.offer(v);
                        prob[v] = prob[u] / nextVerticesCount;
                    }
                }
                if (nextVerticesCount > 0) prob[u] = 0; // frog don't stay vertex u, he keeps going to the next vertex
            }
        }
        return prob[target - 1];
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}};
        FrogPositionAfterTSecs frogPositionAfterTSecs = new FrogPositionAfterTSecs();
        System.out.println(frogPositionAfterTSecs.frogPosition(7, edges, 20, 6));
    }
}
