package trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 * Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.
 *
 * Input: parent = [-1,0,0,1,1,2], s = "abacbe"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
 * It can be proven that there is no longer path that satisfies the conditions.
 *
 * Input: parent = [-1,0,0,0], s = "aabc"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.
 *
 */
public class LongestPathWithNonAdjacentCharacters {
    int longestPathValue = 1; // variable to store the length of the longest path

    public int longestPath(int[] parent, String s) {
        // create an adjacency list representation of the tree
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int i = 1; i < parent.length; i++){
            int j = parent[i];
            adj.putIfAbsent(j, new LinkedList<>());
            adj.get(j).add(i);
        }
        // call dfs on the root of the tree
        dfs(0, adj, s);
        return longestPathValue;
    }

    /*
        For each node consider the longest chain and second  longest chain from the children below it.
        Longest path at that node will be longest + secongLongest + 1.
        Bubble it upwards.
     */
    public int dfs(int node, Map<Integer, List<Integer>> adj, String s){
        // if the node is a leaf node, return 1
        if(!adj.containsKey(node)) return 1;
        int max = 0, secondMax = 0;
        // for each neighbor of the node
        for(int nbrNode : adj.get(node)){
            int longestPathFromNbrNode = dfs(nbrNode , adj, s);
            // if the characters at the current node and its neighbor are the same, ignore the neighbor
            if(s.charAt(node) == s.charAt(nbrNode)) continue;
            // update max and secondMax with the longest path from the neighbor node
            if(longestPathFromNbrNode > max){
                secondMax = max;
                max = longestPathFromNbrNode;
            }else if(longestPathFromNbrNode > secondMax){
                secondMax = longestPathFromNbrNode;
            }
        }
        // update longestPathValue with the longest path that includes the current node
        longestPathValue = Math.max(longestPathValue, max+secondMax+1);
        return max+1;
    }
}
