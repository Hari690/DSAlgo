package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities
 * (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too
 * narrow.
 *
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 *
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 *
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
 *
 * It's guaranteed that each city can reach city 0 after reorder.
 */

/*
    The idea here is to do a DFS and check for outbound edges since there can only be one path between
    2 cities and then count them.
    In terms of doing DFS from final destination and checking for all neighbours
    it requires an adjacency list containing both source, destination and destination, source
    so we can reach all nodes.
 */
public class ReorderRoutesSameDestination {
    int count = 0;
    public int minReorder(int n, int[][] connections) {
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Set<String> edges = new HashSet<>();

        for(int[] conn : connections) {
            List<Integer> list1 = adjList.get(conn[0]);
            if(list1==null)
                list1 = new ArrayList<>();
            list1.add(conn[1]);
            adjList.put(conn[0],list1);
            List<Integer> list2 = adjList.get(conn[1]);
            if(list2==null)
                list2 = new ArrayList<>();
            list2.add(conn[0]);
            adjList.put(conn[1],list2);
            edges.add(conn[0]+" "+conn[1]);
        }

        visited.add(0);
        dfs(0, adjList, visited, edges);
        return count;

    }

    private void dfs(Integer node, Map<Integer,List<Integer>> adjList, Set<Integer> visited, Set<String> edges) {
        List<Integer> neighbours = adjList.get(node);

        for(Integer neighbour : neighbours) {
            if(!visited.contains(neighbour)) {
                if(!edges.contains(neighbour+" "+node))
                    count++;
                visited.add(neighbour);
                dfs(neighbour, adjList, visited, edges);
            }
        }
    }
}
