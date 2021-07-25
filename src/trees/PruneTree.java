package trees;

public class PruneTree {
    public TreeNode pruneTree(TreeNode root) {
        if(root==null)
            return null;

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if(root.left!=null || root.right!=null)
            return root;

        if(root.val==0)
            return null;
        else
            return root;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(0);
        node.right = new TreeNode(11);
        node.left.left = new TreeNode(0);
        node.left.right = new TreeNode(0);
        node.right.left = new TreeNode(0);
        node.right.right = new TreeNode(111);

        TreeNode returnNode = new PruneTree().pruneTree(node);
        System.out.println(returnNode);
    }
}
