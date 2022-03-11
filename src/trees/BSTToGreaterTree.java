package trees;

/**
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the
 * original key plus the sum of all keys greater than the original key in BST.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
public class BSTToGreaterTree {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        change(root);
        return root;
    }

    private void change(TreeNode node) {
        if(node==null)
            return;

        change(node.right);
        sum+=node.val;
        node.val=sum;
        change(node.left);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(1);
        node.right = new TreeNode(6);
        node.right.left = new TreeNode( 5);
        node.right.right = new TreeNode( 7);
        node.right.right.right = new TreeNode( 8);
        node.left.left = new TreeNode( 0);
        node.left.right = new TreeNode( 2);
        node.left.right.right = new TreeNode( 3);

        BSTToGreaterTree solution = new BSTToGreaterTree();
        solution.convertBST(node);
    }
}
