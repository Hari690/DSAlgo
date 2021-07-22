package trees;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through
 * the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 */
public class BinaryTreeDiameter {
    int maxLength = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diamater(root);
        return maxLength;
    }

    public int diamater(TreeNode node) {
        if(node==null)
            return 0;

        int left = diamater(node.left);
        int right = diamater(node.right);

        maxLength = Math.max(maxLength, left+right);

        return Math.max(left,right)+1;
    }
}
