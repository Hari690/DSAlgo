package trees;

/**
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree.
 * Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion.
 * You can return any of them.
 *
 * Input: root = [4,2,7,1,3], val = 5
 * Output: [4,2,7,1,3,5]
 *
 * Input: root = [40,20,60,10,30,50,70], val = 25
 * Output: [40,20,60,10,30,50,70,null,null,25]
 */
public class InsertIntoBST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null)
            return new TreeNode(val);
        return iterate(root,val);
    }

    private TreeNode iterate(TreeNode root, int val) {
        if(root==null)
            return null;

        TreeNode node;
        if(root.val>val)
            node = iterate(root.left, val);
        else
            node = iterate(root.right, val);
        if(node==null) {
            if(root.val>val)
                root.left = new TreeNode(val);
            else
                root.right = new TreeNode(val);
        }
        return root;
    }
}
