package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * You are given a graph that started as a tree with n nodes labeled from 1 to n,
 * with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes.
 * If there are multiple answers, return the answer that occurs last in the input.
 */
public class FindRedundantConnectionCheckCycle {
    /*
        Intuition - Unliked directed graph here we need a 2 way adjacency list.
        Then we do DFS also passing the parent.
        Parent needs to be skipped while doing DFS to prevent going back and forming cycle.
        While creating adjacency list just check if we can reach same node in visited set again and if yes return true.
     */
    public int[] findRedundantConnection(int[][] edges) {
        int[] visited = new int[edges.length+1];

        Map<Integer,Set<Integer>> adjList = new HashMap<>();

        for(int i=0;i<edges.length;i++) {
            Set<Integer> set1 = adjList.get(edges[i][0]);
            Set<Integer> set2 = adjList.get(edges[i][1]);
            if(set1==null){
                set1 = new HashSet<>();
                adjList.put(edges[i][0], set1);
            }
            if(set2==null) {
                set2 = new HashSet<>();
                adjList.put(edges[i][1], set2);
            }
            set1.add(edges[i][1]);
            set2.add(edges[i][0]);
            boolean check = checkCycle(adjList, visited, edges[i][1], 0);
            if(check) {
                return edges[i];
            }
            visited = new int[edges.length+1];
        }
        return new int[2];
    }

    private boolean checkCycle(Map<Integer,Set<Integer>> edges, int[] visited, int i, int parent) {
        if(visited[i]==1)
            return true;

        Set<Integer> neighbours = edges.get(i);
        for(Integer friend : neighbours) {
            if(parent==friend)
                continue;
            visited[i] = 1;
            boolean result = checkCycle(edges, visited, friend, i);
            if(result)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        FindRedundantConnectionCheckCycle findRedundantConnection = new FindRedundantConnectionCheckCycle();
        int[][] edges = {{1,2},{1,3},{2,3}};
        findRedundantConnection.findRedundantConnection(edges);
    }
}
