package trees;

/**
 * Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent. If there are no nodes with an even-valued grandparent, return 0.
 * A grandparent of a node is the parent of its parent if it exists.
 *
 * Example 1:
 *
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 18
 * Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
 */
public class SumEvenGrandParent {
    int sum = 0;
    public int sumEvenGrandparent(TreeNode root) {
        sum(root, null, null);
        return sum;
    }

    private void sum(TreeNode root,TreeNode parent, TreeNode grandParent) {
        if(root==null)
            return;

        if(grandParent!=null)
            sum+=root.val;

        grandParent = parent;
        if(root.val%2==0)
            parent = root;
        else
            parent = null;

        sum(root.left, parent, grandParent);
        sum(root.right, parent, grandParent);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(7);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(3);

        SumEvenGrandParent sumEvenGrandParent = new SumEvenGrandParent();
        sumEvenGrandParent.sumEvenGrandparent(root);
    }
}
