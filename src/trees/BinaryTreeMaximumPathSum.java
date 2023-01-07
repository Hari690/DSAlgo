package trees;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node
 * can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any path.
 */
public class BinaryTreeMaximumPathSum {
    int maxSum;
    public int maxPathSum(TreeNode root) {
        maxSum=Integer.MIN_VALUE;
        findMaxPathSum(root);
        return maxSum;
    }

    public int findMaxPathSum(TreeNode root) {
        if(root==null) {
            return 0;
        }
        // in case there are negative values in the children don't pass their value upwards.
        int left = Math.max(0,findMaxPathSum(root.left));
        int right = Math.max(0,findMaxPathSum(root.right));
        // for the node itself we can calculate the value plus left plus right as it does not involve any split
        // but only pass the higher value upwards as it's representative of no splitting
        maxSum = Math.max(maxSum,left+right+root.val);
        // Prune the part of tree that's shorter and pass only larger value as it's max path parent to child.
        return Math.max(left,right)+root.val;
    }
}
