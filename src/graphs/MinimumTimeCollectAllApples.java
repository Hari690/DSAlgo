package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.
 *
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.
 */
public class MinimumTimeCollectAllApples {

    /*
        Here the idea is to identify the path which contains the apples so we need to check all paths,
        it's not shortest path algorithm.
        If we use BFS then we will add all levels of given BFS until last apple i.e even the nodes which do not
        have apple as children.
        So it's better to use DFS after creating bidirectional adjList and add in case
        current node has value or if nodes below has value.
        Each node having apple or having a child having apple adds 2 to the answer.
        Collect node 0 does not need to consume any time
     */
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        for(int[] edge : edges) {
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adjList.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        hasApple.set(0,false);
        return dfs(0, adjList, hasApple, visited);
    }

    private int dfs(int node, Map<Integer,List<Integer>> adjList, List<Boolean> hasApple, Set<Integer> visited) {
        int total = 0;
        visited.add(node);
        for(Integer child : adjList.getOrDefault(node, new ArrayList<>())) {
            if(!visited.contains(child)) {
                total += dfs(child, adjList, hasApple, visited);
            }
        }
        if((hasApple.get(node) || total>0) && node!=0)
            total+=2;
        return total;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,2},{0,3},{1,2}};
        List<Boolean> hasApple = List.of(false,true,false,false);
        MinimumTimeCollectAllApples solution = new MinimumTimeCollectAllApples();
        solution.minTime(4, edges, hasApple);
    }
}
