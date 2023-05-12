package trees;

import java.util.ArrayDeque;

/**
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
 *
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
 *
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node t.
 *
 * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * Output: "UURL"
 * Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
 *
 * Input: root = [2,1], startValue = 2, destValue = 1
 * Output: "L"
 * Explanation: The shortest path is: 2 → 1.
 */
public class ShortestPathBetween2NodesInBinaryTree {

    /*
        Find LCA between both nodes.
        Find both path to nodes starting from LCA.
        Change first node's path to U and append second path.
     */
    public String getDirections(TreeNode root, int s, int d) {
        TreeNode CA = findLCA(root, s, d);

        StringBuilder ans = new StringBuilder();
        ArrayDeque<String> q1 = new ArrayDeque<>();
        helper(CA, s, q1);
        ArrayDeque<String> q2 = new ArrayDeque<>();
        helper(CA, d, q2);

        for(int i = 0; i < q1.size(); i++) ans.append("U");
        while(!q2.isEmpty()) ans.append(q2.poll());

        return ans.toString();
    }

    public boolean helper(TreeNode n, int v, ArrayDeque<String> q) {
        if(n == null) return false;
        if(n.val == v) return true;

        q.offer("L");
        boolean left = helper(n.left, v, q);
        if(left) return true;
        q.removeLast();

        q.offer("R");
        boolean right = helper(n.right, v, q);
        if(right) return true;
        q.removeLast();

        return false;
    }

    public TreeNode findLCA(TreeNode node, int s, int d) {
        if(node == null) return null;

        if(node.val == s || node.val == d) return node;

        TreeNode left = findLCA(node.left, s, d);
        TreeNode right = findLCA(node.right, s, d);
        if(left != null && right != null) return node;
        if(left == null && right != null) return right;
        else return left;
    }
}
