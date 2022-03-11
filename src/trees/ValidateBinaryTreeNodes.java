package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.
 *
 * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
 *
 * Note that the nodes have no values and that we only use the node numbers in this problem.
 *
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 * Output: true
 *
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 * Output: false
 */
public class ValidateBinaryTreeNodes {

    /* Start DFS or BFS from node with indegree(incoming edges) 0 which is root.
        There should only be one such node.
     */
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {

        Map<Integer,List<Integer>> map = new HashMap<>();
        int[] inDegree = new int[n];
        for(int i=0;i<n;i++) {
            List<Integer> children = new ArrayList<>();
            if(leftChild[i]!=-1) {
                children.add(leftChild[i]);
                inDegree[leftChild[i]]++;
            }
            if(rightChild[i]!=-1) {
                children.add(rightChild[i]);
                inDegree[rightChild[i]]++;
            }
            map.put(i, children);
        }

        // Find the root node
        int noInDegreeNode = 0;
        int node = 0;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                node = i;
                noInDegreeNode++;
            }
        }
        // If and only if one node should have zero inDegree
        if (noInDegreeNode != 1) return false;

        Set<Integer> visited = new HashSet<>();

        boolean result = dfs(node, visited, map);
        if(!result)
            return false;
        return result && visited.size()==n;
    }

    private boolean dfs(int node, Set<Integer> visited, Map<Integer,List<Integer>> map) {
        visited.add(node);
        List<Integer> children = map.get(node);
        if(children==null)
            return true;
        for(int child : children) {
            if(visited.contains(child))
                return false;
            boolean result = dfs(child, visited, map);
            if(!result)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidateBinaryTreeNodes solution = new ValidateBinaryTreeNodes();
        int[] leftChild = {3,-1,1,-1};
        int[] rightChild = {-1,-1,0,-1};
        solution.validateBinaryTreeNodes(4, leftChild, rightChild);
    }
}
