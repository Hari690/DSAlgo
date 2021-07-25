package trees;

/**
 * Given the root of a binary tree, return the sum of every tree node's tilt.
 *
 * The tilt of a tree node is the absolute difference between the sum of all left subtree node values and all right subtree node values.
 * If a node does not have a left child, then the sum of the left subtree node values is treated as 0. The rule is similar if there the
 * node does not have a right child.
 */
public class BinaryTreeTilt {
    int sum = 0;
    public int findTilt(TreeNode root) {
        recurse(root);
        return sum;
    }

    public int recurse(TreeNode root) {
        if(root==null)
            return 0;

        int lVal = recurse(root.left);
        int rVal = recurse(root.right);

        sum += ((lVal-rVal)>=0)?(lVal-rVal):(rVal-lVal);

        return root.val+lVal+rVal;

    }
}
