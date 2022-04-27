package trees;

/**
 * Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree,
 * and every node has no left child and only one right child.
 */
public class IncreasingOrderSearchTree {
    TreeNode prev = null;
    TreeNode root1 = null;
    public TreeNode increasingBST(TreeNode root) {
        rearrange(root);
        return root1;
    }

    private void rearrange(TreeNode node) {
        if(node==null)
            return;
        rearrange(node.left);
        if(prev==null) {
            prev = node;
            prev.left=null;
            //prev.right=null;
            root1 = node;
        }
        else  {
            prev.right = node;
            node.left=null; // we no  longer needs the left  side of the node, so set it to null
            prev = node;
        }
        rearrange(node.right);
    }
}
