package trees;

/**
 * Given the root of a Binary Search Tree (BST),
 * return the minimum absolute difference between the values of any two different nodes in the tree.
 */
public class MinAbsDiffBST {
    int min = Integer.MAX_VALUE;
    Integer prev=null;
    public int getMinimumDifference(TreeNode root) {
        getMinAbsDiff(root);
        return min;
    }

    private void getMinAbsDiff(TreeNode root) {
        if(root==null)
            return;
        getMinAbsDiff(root.left);
        if(prev!=null) {
            min = Math.min(min,Math.abs(root.val-prev));
        }
        prev = root.val;
        getMinAbsDiff(root.right);
    }
}
