package trees;

public class PathSum {

    public static void main(String[] args) {

        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(4);
        node.right = new TreeNode(8);
        node.left.left = new TreeNode(11);
        node.right.left = new TreeNode(13);
        node.right.right = new TreeNode(4);
        node.left.left.left = new TreeNode(7);
        node.left.left.right = new TreeNode(2);
        node.right.right.right = new TreeNode(1);

//        problems.TreeNode node = new problems.TreeNode(1);
//        node.left = new problems.TreeNode(2);

        boolean balanced = new PathSum().hasPathSum(node,1);
        System.out.println(balanced);

    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }
        return hasPathSum1(root,0,sum);
    }

    public boolean hasPathSum1(TreeNode root, int sum, int total) {
        if(root==null) {
            return false;
        }
        if(root.left==null && root.right==null) {
            if ((sum + root.val) == total) {
                return true;
            } else {
                return false;
            }
        }
        return hasPathSum1(root.left, sum + root.val, total) || hasPathSum1(root.right, sum + root.val, total);

    }

}
