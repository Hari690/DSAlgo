package trees;

public class MergeBinaryTrees {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return mergeTrees(t1, t2, new TreeNode());
    }

    private TreeNode mergeTrees(TreeNode t1, TreeNode t2, TreeNode t3) {
        if( t1==null )
            return t2;
        else if(t2==null)
            return t1;
        else {
            t3.val = t1.val+t2.val;
        }

        t3.left = mergeTrees(t1.left,t2.left, new TreeNode());
        t3.right = mergeTrees(t1.right,t2.right, new TreeNode());

        return t3;
    }
}
