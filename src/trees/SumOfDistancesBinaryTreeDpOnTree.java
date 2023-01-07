package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 * You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 * Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.
 * Example 1:
 *         0
 *       1   2
 *         3 4 5
 * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation: The tree is shown above.
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.
 * Hence, answer[0] = 8, and so on.
 * Example 2:
 * Input: n = 1, edges = []
 * Output: [0]
 * Example 3:
 * Input: n = 2, edges = [[1,0]]
 * Output: [1,1]
 */
public class SumOfDistancesBinaryTreeDpOnTree {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        // build graph and declare results
        final List<Integer>[] graph = new ArrayList[n];
        final int[] count = new int[n];
        final int[] answer = new int[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        postOrder(0, -1, graph, count, answer);
        // after postOrder, only answer[root] is correct, so do preOrder to update answer
        preOrder(0, -1, graph, count, answer, n);

        return answer;
    }

    // set count - total of no of nodes children to this node and current , contribution of each node is 1.
    // set answer - total of distance of nodes below current node prefilled with 0.
    private void postOrder(int node, int parent, List<Integer>[] graph, int[] count, int[] answer) {
        for (int child : graph[node]) {
            if (child != parent) {
                postOrder(child, node, graph, count, answer);
                count[node] += count[child];
                // look at the diagram in example 1.
                answer[node] += answer[child] + count[child];
            }
        }
        count[node]++;
    }

    // answers are filled in array so we can do preorder
    // contribution of each node is contribution of parent minus no of nodes getting closer plus nodes that are going farther
    // eg for 1 it will be 8 + 5 - 1 = 12
    private void preOrder(int node, int parent, List<Integer>[] graph, int[] count, int[] answer, int n) {
        for (int child : graph[node]) {
            if (child != parent) {
                answer[child] = answer[node] + (n - count[child]) - count[child];
                preOrder(child, node, graph, count, answer, n);
            }
        }
    }

    public static void main(String[] args) {
        SumOfDistancesBinaryTreeDpOnTree sumOfDistancesBinaryTree = new SumOfDistancesBinaryTreeDpOnTree();
        int[][] distances = {{0,1},{0,2},{2,3},{2,4},{2,5}};
        sumOfDistancesBinaryTree.sumOfDistancesInTree(6, distances);
    }
}
