package trees;

/**
 * You are given the root of a binary search tree (BST), where the values of exactly two
 * nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
 */
class RecoverBST {
    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode prev=null;

    /*
        The first element is always larger than its next one while the
        second element is always smaller than its previous one.
     */
    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode root) {

        if (root == null)
            return;

        inorder(root.left);
        if (prev!=null && first == null && prev.val >= root.val) {
            first = prev;
        }

        if (first != null && prev.val >= root.val) {
            second = root;
        }
        prev = root;

        inorder(root.right);
    }
}