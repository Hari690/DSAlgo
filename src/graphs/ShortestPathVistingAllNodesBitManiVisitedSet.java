package graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 * Input: graph = [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1,0,2,0,3]
 *
 * Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 *
 */
public class ShortestPathVistingAllNodesBitManiVisitedSet {
    /*
        Simple BFS with visited set will not work since we need to repeat some paths to cover all paths.
        But we also have to avoid cycles.
        So we store the current state in a bit and check for going to another neighbour this state has been seen before.
        If we apply simple bfs starting from any node(say 0) where we also keep track of visited array (Consecutively meaning we can't visit them again) it will never lead us to the solution.
        On the other hand if we apply bfs from any node (say 0) and don't keep the track of visited array,
        it will lead to cycle [ {0}->{0,1}->{0,1,0}->{0,1,0,1} and so on]

        Since 1 <= n <= 12 we can use bits for storing the path visited

        In nutshell, for each current head(or leading) node we must maintain a space where we can check whether we visited that set of path again or not).
     */
    public int shortestPathLength(int[][] graph) {
        if(graph.length==1)
            return 0;

        int finalState = (1 << graph.length) - 1;
        // {i, BitState}
        Queue<int []> queue = new LinkedList<>();

        //Adding all nodes initially because we can start anywhere.
        for(int i=0; i<graph.length; i++) {
            queue.add(new int [] {i, 1 << i});

        }

        //[no of many nodes][each node may have 2^n visited bit]
        int [][] visitedMap = new int [graph.length][finalState+1];

        int shortestPath = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            shortestPath++;
            for(int i=0; i<size; i++){
                int [] head = queue.poll();
                int nodeId = head[0];
                int visitedNodeBitState = head[1];
                for(int neighbor : graph[nodeId]){
                    int newVisitedNodeBitState = visitedNodeBitState | (1 << neighbor);
                    //If the same node was visited again with same visitedNodeBit, it means this node can be skipped, For example: 1->0->1->0.
                    // First 1 we have {1, 10}, then we have {0, 11}, then we will have {1, 11}. Lastly, we have {0, 11} which is a state
                    // we already had before. So we don't visit this again.
                    if(visitedMap[neighbor][newVisitedNodeBitState] == 1)
                        continue;
                    visitedMap[neighbor][newVisitedNodeBitState] = 1;
                    if(newVisitedNodeBitState==finalState)
                        return shortestPath;
                    queue.add(new int [] {neighbor, newVisitedNodeBitState});
                }
            }
        }
        return -1;
    }
}
