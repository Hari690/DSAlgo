package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * We are given a binary tree (with root node root), a target node, and an integer value k.
 *
 * Return a list of the values of all nodes that have a distance k from the target node.  The answer can be returned in any order.
 */
public class NodesDistanceK {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            Map<TreeNode,TreeNode> parentMap = new HashMap<>();
            helper(root.left, parentMap, root);
            helper(root.right, parentMap, root);

            Set<TreeNode> visited = new HashSet<>();
            List<Integer> output = new ArrayList<>();
            fetchDistanceKNodes(target,k,0,parentMap,visited,output);
            return new ArrayList<>(output);
        }

        public void helper(TreeNode root, Map<TreeNode,TreeNode> parentMap, TreeNode parent) {
            if ( root==null) {
                return;
            }
            parentMap.putIfAbsent(root, parent);
            helper(root.left, parentMap, root);
            helper(root.right, parentMap, root);
        }

        public void fetchDistanceKNodes(TreeNode target, int k, int currentLevel, Map<TreeNode,TreeNode> parentMap, Set<TreeNode> visited, List<Integer> output) {
            if ( target==null || currentLevel>k) {
                return;
            }
            visited.add(target);
            if(currentLevel == k) {
                output.add(target.val);
                return;
            }
            if(!visited.contains(parentMap.get(target))) {
                fetchDistanceKNodes(parentMap.get(target),k,currentLevel+1,parentMap, visited, output);
            }
            if(!visited.contains(target.left)) {
                fetchDistanceKNodes(target.left,k,currentLevel+1,parentMap, visited, output);
            }
            if(!visited.contains(target.right)) {
                fetchDistanceKNodes(target.right,k,currentLevel+1,parentMap, visited, output);
            }
        }
    }
}
