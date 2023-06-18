package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.
 * Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique solution exists.
 * Notice that you can return the vertices in any order.
 * Example 1:
 * Input: n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
 * Output: [0,3]
 * Explanation: It's not possible to reach all the nodes from a single vertex. From 0 we can reach [0,1,2,5]. From 3 we can reach [3,4,2,5]. So we output [0,3].
 *
 * Example 2:
 * Input: n = 5, edges = [[0,1],[2,1],[3,1],[1,4],[2,4]]
 * Output: [0,2,3]
 * Explanation: Notice that vertices 0, 3 and 2 are not reachable from any other node, so we must include them. Also any of these vertices can reach nodes 1 and 4.
 */
public class MinimumNumberOfVerticesReachAllNodes {

    /*
    Idea is following - if there is an edge coming to a vertex then it's reachable from some other node, but if vertex has only outgoing edges then it can't be reached from others and it must be the part of the result.
    Algorithm is following - count in-degrees of every vertex, those that have 0 at the end are part of the answer.
    To simplify things we can use booleans to represent the in-degree, if it's greater than 0 set the flag to true because we are not interested in exact degree value.

    O(V+E) - need to check every edge and every vertex.
    O(n) space - need to keep in-degree flags for every node.
     */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        Map<Integer,Integer> in = new HashMap<>();

        for(List<Integer> list : edges) {
            in.put(list.get(1),in.getOrDefault(list.get(1),0)+1);
        }

        List<Integer> result = new ArrayList<>();

        for(int i=0;i<=n-1;i++) {
            if(!in.containsKey(i))
                result.add(i);
        }

        return result;
    }
}
