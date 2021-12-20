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

    class Output {
        boolean output = true;
    }

    public boolean isBalanced(TreeNode root) {
        Output output = new Output();
        balanced(root, output);
        return output.output;
    }


    private int balanced(TreeNode root, Output output) {
        if(root==null)
            return 0;

        if(output.output) {
            int left = balanced(root.left, output);
            int right = balanced(root.right, output);
            int diff = Math.abs(left-right);
            if(diff>1) {
                output.output = false;
            }
            return Math.max(left,right)+1;
        }
        return 0;
    }
}
