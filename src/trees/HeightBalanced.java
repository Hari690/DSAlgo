package trees;

class Height {
    int height;
}

public class HeightBalanced {

    public static void main(String[] args) {

        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.left.left = null;
        node.left.right = null;
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);

        boolean balanced = new HeightBalanced().isBalanced(node);
        System.out.println(balanced);
    }

    public boolean isBalanced(TreeNode root) {

       return isBalanced(root, new Height());

    }

    private boolean isBalanced(TreeNode root, Height height) {

        if(root==null){
            height.height = 0;
            return true;
        }

        Height lheight = new Height();
        Height rheight = new Height();
        // way of getting back 2 things. is balanced on the left as well as lheight by passing
        // a mutable object with an integer.
        boolean lBalanced = isBalanced(root.left, lheight);
        boolean rBalanced = isBalanced(root.right, rheight);
        int lh = lheight.height, rh = rheight.height;

        height.height = (lh > rh ? lh : rh) + 1;

        if(Math.abs(lheight.height-rheight.height)>=2) {
            return false;
        } else {
            return lBalanced && rBalanced;
        }
    }
}
