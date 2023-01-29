package graphs;

import java.util.Arrays;

/**
 * You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
 * The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.
 * You are also given two integers node1 and node2.
 * Return the index of the node that can be reached from both node1 and node2, such that the maximum between the distance from node1 to that node, and from node2 to that node is minimized. If there are multiple answers, return the node with the smallest index, and if no possible answer exists, return -1.
 * Note that edges may contain cycles.
 *
 * Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
 * Output: 2
 * Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
 * The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum distance than 1, so we return node 2.
 *
 * Input: edges = [1,2,-1], node1 = 0, node2 = 2
 * Output: 2
 * Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
 * The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum distance than 2, so we return node 2.
 */
public class FindClosestNodeToGivenNode {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int[] distance1 = new int[edges.length];
        Arrays.fill(distance1, -1);
        dfs(edges, node1, distance1,0);

        int[] distance2 = new int[edges.length];
        Arrays.fill(distance2, -1);
        dfs(edges, node2, distance2,0);

        int min = Integer.MAX_VALUE;
        int index = -1;
        for(int i=0;i<distance1.length;i++) {
            if(distance1[i]!=-1 && distance2[i]!=-1 && Math.max(distance1[i],distance2[i])<min) {
                min = Math.max(distance1[i],distance2[i]);
                index = i;
            }
        }
        return index;
    }

    private void dfs(int[] edges, int node, int[] distance, int d) {
        distance[node] = d;
        int val = edges[node];
        if(val!=-1 && distance[val]==-1)
            dfs(edges, val, distance, d+1);
    }

    public static void main(String[] args) {
        FindClosestNodeToGivenNode findClosestNodeToGivenNode = new FindClosestNodeToGivenNode();
        findClosestNodeToGivenNode.closestMeetingNode(new int[]{2,2,3,-1},0,1);
    }
}
