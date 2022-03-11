package trees;

/**
 * Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node
 * in the tree is now the root of the tree, and every node has no left child and only one right child.
 */
public class IncOrderSearchTree {
    TreeNode prev = null;
    TreeNode head = null;
    public TreeNode increasingBST(TreeNode root) {
        rearrange(root);
        prev.left = null;
        return head;
    }

    private void rearrange(TreeNode root) {
        if(root==null)
            return;

        rearrange(root.left);
        System.out.println(root.val+" "+((prev==null)?"":prev.val));
        if(prev==null) {
            prev = root;
            head = root;
        }
        else {
            prev.right=root;
            prev.left=null;
            prev = root;
        }
        rearrange(root.right);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(2);
        node.left = new TreeNode(1);
        node.right = new TreeNode(4);
        node.right.left = new TreeNode( 3);

        IncOrderSearchTree incOrderSearchTree = new IncOrderSearchTree();
        incOrderSearchTree.increasingBST(node);
    }
}
