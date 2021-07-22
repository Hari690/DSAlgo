package trees;

import problems.TreeNode;

public class BinaryTreeIsBinarySearchTree {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(1);
        //node.right = new problems.TreeNode(3);
        System.out.println(new BinaryTreeIsBinarySearchTree().isValidBST(node));
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long minValue, long maxValue) {

        if(root==null) {
            return true;
        }

        if(!(root.val<maxValue && root.val>minValue)) {
            return false;
        }

        return isValidBST(root.left,minValue,root.val) && isValidBST(root.right,root.val,maxValue);
    }
}
