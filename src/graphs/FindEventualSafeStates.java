package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].
 * A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node.
 * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 *
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Explanation: The given graph is shown above.
 * Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
 * Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
 * Example 2:
 *
 * Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * Output: [4]
 * Explanation:
 * Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
 */
public class FindEventualSafeStates {

    /*
        A node is a safe node if every possible path starting from that node leads to a terminal node.
        It means there should not be a cycle with that node.
        Graph cycle + Dp.
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> list = new ArrayList<>();
        Map<Integer,Boolean> visited = new HashMap<>();
        for(int i=0;i<graph.length;i++) {
            if(isSafe(i, graph, visited))
                list.add(i);
        }
        return list;
    }

    private boolean isSafe(int node,int[][] graph, Map<Integer,Boolean> visited) {
        if(visited.containsKey(node))
            return visited.get(node);
        // mark as visiting
        visited.put(node, false);
        for(int neighbour : graph[node]) {
            if(!isSafe(neighbour, graph, visited))
                return false;
        }
        // mark as visited
        visited.put(node, true);
        return true;
    }

    public static void main(String[] args) {
        FindEventualSafeStates solution = new FindEventualSafeStates();
        int[][] graphs = {{},{0,2,3,4},{3},{4},{}};
        solution.eventualSafeNodes(graphs);
    }
}
