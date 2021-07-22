package graphs;

import java.util.ArrayList;
import java.util.List;

/*
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
find all possible paths from node 0 to node n - 1, and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i
(i.e., there is a directed edge from node i to node graph[i][j]).

Example input
[[1,2],[3],[3],[]]
 */
public class AllPathsSourceToTarget {
    private void dfs(int[][] graph, List<List<Integer>> result, List<Integer> path, int u){
        path.add(u);
        if(u == graph.length-1) result.add(new ArrayList(path));
        else
            for(int v: graph[u]) dfs(graph, result, path, v);
        path.remove(path.size()-1);
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList();
        List<Integer> path = new ArrayList();
        dfs(graph, result, path, 0);
        return result;
    }

    public static void main(String[] args) {
        int[][] adjMatrix = {{1,2},{3},{3},{}};
        AllPathsSourceToTarget paths = new AllPathsSourceToTarget();
        System.out.println(paths.allPathsSourceTarget(adjMatrix));
    }
}
