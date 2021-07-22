package trees;

public class PruneTree {
    public TreeNode pruneTree(TreeNode root) {
        boolean val = helper(root);
        if( val==true) {
            return null;
        } else {
            return root;
        }
    }

    private boolean helper(TreeNode root) {
        if( root==null ) {
            return true;
        }

        boolean leftHasZero = helper(root.left);
        boolean rightHasZero = helper(root.right);

        if( root.val==0) {
            if(leftHasZero && rightHasZero)
                return true;
            else {
                if (leftHasZero) {
                    root.left = null;
                } else if (rightHasZero) {
                    root.right = null;
                }
                return false;
            }
        } else {
            // case for 1
            if(leftHasZero && rightHasZero) {
                root.left = null;
                root.right = null;
            } else {
                if (leftHasZero) {
                    root.left = null;
                } else if (rightHasZero) {
                    root.right = null;
                }
            }
            return false;
        }
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
