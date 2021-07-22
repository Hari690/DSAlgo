package trees;

public class LowestCommonAncestor {

    public static void main(String[] args) {

        TreeNode node = new TreeNode(6);
        node.left = new TreeNode(2);
        node.right = new TreeNode(8);
        node.left.left = new TreeNode(0);
        node.left.right = new TreeNode(4);
        node.right.left = new TreeNode(7);
        node.right.right = new TreeNode(9);
        node.left.left.left = new TreeNode(5);
        node.left.left.right = new TreeNode(11);

        new LowestCommonAncestor().lowestCommonAncestor(node, node.left,  node.right);

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode lca = lowestCommonAncestorCheck(root, p, q);
        System.out.println(lca.val);

        return lca;
    }

    private TreeNode lowestCommonAncestorCheck(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }

        if(root == p) {
           return p;
        }

        if(root == q) {
           return q;
        }

        // i.e present node is not one of the one's we are checking for.
        // so we check against the results in left and right side.

        TreeNode result1 = lowestCommonAncestorCheck(root.left, p, q);
        TreeNode result2 = lowestCommonAncestorCheck(root.right, p, q);

        if(result1!=null && result2!=null) {
            return root;
        }

        if(result1!=null)
            return result1;

        if(result2!=null)
            return result2;

        return null;

    }
}
