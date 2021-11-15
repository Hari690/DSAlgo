package trees;

/**
 * Given the root of a Binary Search Tree (BST), return the minimum difference
 * between the values of any two different nodes in the tree.
 */
public class BSTMinDiff {
    int minDiff = Integer.MAX_VALUE;
    Integer prev=null;
    Integer current=null;
    public int minDiffInBST(TreeNode root) {
        minDiff(root);
        return minDiff;
    }

    private void minDiff(TreeNode root) {
        if(root==null)
            return;
        minDiff(root.left);
        // since it's BST do the checks here so we can check in sorted order.
        prev = current;
        current = root.val;
        if(prev!=null)
            minDiff=Math.min(minDiff,Math.abs(prev-current));
        minDiff(root.right);
    }
}
