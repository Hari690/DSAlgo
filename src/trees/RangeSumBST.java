package trees;

public class RangeSumBST {

    public static void main(String[] args) {

        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(5);
        node.right = new TreeNode(15);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(7);
        node.right.right = new TreeNode(18);

        new RangeSumBST().rangeSumBST(node, 7, 15);
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        return rangeSumBSTs(root, low, high);
    }

    public int rangeSumBSTs(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if( root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        if( root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }
}
