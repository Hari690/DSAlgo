package trees;

/**
 * Given the root of a binary tree, construct a string consisting of parenthesis and integers from a binary tree with the preorder
 * traversal way, and return it.
 *
 * Omit all the empty parenthesis pairs that do not affect the one-to-one mapping relationship between the string and the original binary
 * tree.
 */
public class TreeToStr {
    public String tree2str(TreeNode root) {
        return recurse(root);
    }

    private String recurse(TreeNode root) {
        if(root==null)
            return "";
        if(root.left==null && root.right==null)
            return String.valueOf(root.val);
        if(root.right==null)
            return root.val + "(" + recurse(root.left) + ")";
        else
            return root.val + "(" + recurse(root.left) + ")" + "(" + recurse(root.right) + ")";
    }

    public static void main(String[] args) {
        TreeToStr treeToStr = new TreeToStr();

        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);

        treeToStr.tree2str(node);
    }
}
