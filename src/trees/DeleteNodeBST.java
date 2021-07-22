package trees;

/**
 * Find the element to delete, if it's leaf node directly delete.
 * Else find inorder successor or inorder predecessor of node and replace that value in node to delete.
 * After that delete the inorder successor or predecessor.
 */
public class DeleteNodeBST {
    private int inorder_predecessor(TreeNode root){
        root = root.left;
        while(root.right != null) root = root.right;
        return root.val;
    }
    private int inorder_successor(TreeNode root){
        root = root.right;
        while(root.left != null) root = root.left;
        return root.val;
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return root;
        if(key > root.val) root.right = deleteNode(root.right, key);
        else if(key < root.val) root.left = deleteNode(root.left, key);
        else{
            if(root.left == null && root.right == null) root = null; // leaf node
            else if(root.left != null){ // find inorder predecessor
                root.val = inorder_predecessor(root);
                root.left = deleteNode(root.left, root.val); // delete inorder predecessor
            }
            else { // find inorder successor
                root.val = inorder_successor(root);
                root.right = deleteNode(root.right, root.val); // delete inorder successor
            }
        }
        return root;
    }
}
