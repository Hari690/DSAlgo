package graphs;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
 * The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from node i, then edges[i] == -1.
 * Return the length of the longest cycle in the graph. If no cycle exists, return -1.
 * A cycle is a path that starts and ends at the same node.
 *
 * Example 1:
 * Input: edges = [3,3,4,2,3]
 * Output: 3
 * Explanation: The longest cycle in the graph is the cycle: 2 -> 4 -> 3 -> 2.
 * The length of this cycle is 3, so 3 is returned.
 *
 * Input: edges = [2,-1,3,1]
 * Output: -1
 * Explanation: There are no cycles in this graph.
 */
public class LongestCycleInAGraph {

    int maxDistance = -1;
    // calculate distance from origin and check if cycle is formed, if so check if it's the longest.
    // check if a node has been visited again under same "id" to see if there's a cycle. If it's already been visited before skip it.
    // If part of same cycle then use distance from origin to both ends of cycle to calculate length.
    public int longestCycle(int[] edges) {

        Map<Integer, Integer> adjList = new HashMap<>();

        for(int i=0;i<edges.length;i++) {
            if(edges[i]!=-1)
                adjList.put(i, edges[i]);
        }

        Map<Integer,Pair<Integer,Integer>> visited = new HashMap<>();
        for(int i=0;i<edges.length;i++) {
            if(adjList.containsKey(i)) {
                findCycle(i, adjList, visited, 0, i);
            }
        }

        return maxDistance;
    }

    private void findCycle(int n, Map<Integer, Integer> adjList, Map<Integer,Pair<Integer,Integer>> visited, int d, int id) {
        if (visited.containsKey(n)) {
            if(visited.get(n).key==id)
                maxDistance = Math.max(d-visited.get(n).value, maxDistance);
            return;
        }
        visited.put(n, new Pair<>(id,d));

        if(adjList.get(n)!=null) {
            findCycle(adjList.get(n),adjList, visited, d+1, id);
        }
    }

    public static void main(String[] args) {
        LongestCycleInAGraph longestCycleInAGraph = new LongestCycleInAGraph();
        int[] edges = new int[]{-1,4,-1,2,0,4};
        longestCycleInAGraph.longestCycle(edges);
    }
}
