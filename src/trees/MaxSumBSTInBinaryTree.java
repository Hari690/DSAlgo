package trees;

/**
 * Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 * Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * Output: 20
 * Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
 *
 * Example 2:
 * Input: root = [4,3,null,1,2]
 * Output: 2
 * Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
 *
 * Example 3:
 * Input: root = [-4,-2,-5]
 * Output: 0
 * Explanation: All values are negatives. Return an empty BST.
 */
public class MaxSumBSTInBinaryTree {

    class Node {
        int min;
        int max;
        int sum;
        Node(int min, int max, int sum) {
            this.min = min;
            this.max = max;
            this.sum = sum;
        }
    }

    private int maxSum = 0;
    public int maxSumBST(TreeNode root) {
        postOrderTraverse(root);
        return maxSum;
    }
    /*
        We have to do post order because we need to make a decision at every node based on it's children
     */
    private Node postOrderTraverse(TreeNode root) {
        if (root == null)
            return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        Node left = postOrderTraverse(root.left);
        Node right = postOrderTraverse(root.right);
        // The BST is the tree:
        if (left == null             // the left subtree must be BST
                || right == null            // the right subtree must be BST
                || root.val <= left.max       // the root's key must greater than maximum keys of the left subtree
                || root.val >= right.min)    // the root's key must lower than minimum keys of the right subtree
            return null;

        int sum = root.val + left.sum + right.sum; // now it's a BST make `root` as root
        maxSum = Math.max(maxSum, sum);
        int min = Math.min(root.val, left.min);
        int max = Math.max(root.val, right.max);
        return new Node(min, max, sum);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(9);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.right = new TreeNode(2);
        node.left.right.left = new TreeNode(3);
        node.left.right.left.left = new TreeNode(-5);
        node.left.right.left.right = new TreeNode(4);
        node.left.right.left.left.right = new TreeNode(1);
        node.left.right.left.right.right = new TreeNode(10);

        MaxSumBSTInBinaryTree maxSumBSTInBinaryTree = new MaxSumBSTInBinaryTree();
        System.out.println(maxSumBSTInBinaryTree.maxSumBST(node));
    }
}
