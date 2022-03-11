package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
 *
 * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
 *
 * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
 *
 * The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.
 */
public class SmallestSubtreeWithDeepestNodes {
    int maxLevel = 0;
    TreeNode result = null;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        recur(root, 0);
        return result;
    }

    private int recur(TreeNode node, int level) {
        if(node==null)
            return level;

        int maxLeft = recur(node.left, level+1);
        int maxRight = recur(node.right, level+1);

        int currentLevel = Math.max(maxLeft,maxRight);
        maxLevel = Math.max(maxLevel, currentLevel);

        if(maxLevel==currentLevel && maxLeft==maxRight)
            result = node;

        return currentLevel;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(5);
        node.right = new TreeNode(1);
        node.left.left = new TreeNode(6);
        node.left.right = new TreeNode(2);
        node.right.left = new TreeNode(0);
        node.right.right = new TreeNode(8);
        node.left.right.left = new TreeNode(7);
        node.left.right.right = new TreeNode(4);

        SmallestSubtreeWithDeepestNodes solution = new SmallestSubtreeWithDeepestNodes();
        solution.subtreeWithAllDeepest(node);
    }
}
