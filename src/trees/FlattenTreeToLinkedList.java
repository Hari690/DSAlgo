package trees;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left
 * child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */
public class FlattenTreeToLinkedList {
    private TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root==null)
            return;
        flatten(root.right);
        flatten(root.left);
        System.out.println(root.val);
        root.right = prev;
        root.left=null;
        prev = root;
    }
}
