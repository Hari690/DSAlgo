package trees;

/**
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the
 * last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 */
public class CountCompleteTreeNodes {

    // Check level , if both left and right level are same return 2^level-1.
    // Else recur down until this is true and keep counting.
    public int countNodes(TreeNode root) {
        int lMaxLevel = findLevelLeft(root,1);
        int rMaxLevel = findLevelRight(root,1);
        if(lMaxLevel==rMaxLevel)
            return (int)(Math.pow(2,lMaxLevel)-1);
        else
            return 1+countNodes(root.left)+countNodes(root.right);
    }

    public int findLevelLeft(TreeNode root, int level) {
        if(root==null)
            return 0;
        return 1 + findLevelLeft(root.left,level+1);
    }

    public int findLevelRight(TreeNode root, int level) {
        if(root==null)
            return 0;
        return 1 + findLevelRight(root.right,level+1);
    }
}
