package trees;

/**
 * You are given two binary trees root1 and root2.
 *
 * Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. You
 * need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new
 * value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.
 *
 * Return the merged tree.
 *
 * Note: The merging process must start from the root nodes of both trees.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * Output: [3,4,5,5,4,null,7]
 * Example 2:
 *
 * Input: root1 = [1], root2 = [1,2]
 * Output: [2,2]
 */
public class MergeTrees {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root2==null)
            return root1;
        merge(root1, root2, null, true);
        return root2;
    }

    private void merge(TreeNode root1, TreeNode root2, TreeNode parent, boolean isLeft) {
        if(root1==null && root2==null)
            return;

        if(root1!=null && root2==null) {
            TreeNode node = new TreeNode();
            node.val = root1.val;
            if(isLeft)
                parent.left = node;
            else
                parent.right = node;
            root2=node;
        }
        else if(root1!=null && root2!=null)
            root2.val+= root1.val;

        merge((root1==null)?null:root1.left, (root2==null)?null:root2.left, root2, true);
        merge((root1==null)?null:root1.right, (root2==null)?null:root2.right, root2, false);
    }

    //alternative solution
    public TreeNode mergeTreesReturn(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;

        int val = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);
        TreeNode newNode = new TreeNode(val);

        newNode.left = mergeTreesReturn(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        newNode.right = mergeTreesReturn(t1 == null ? null : t1.right, t2 == null ? null : t2.right);

        return newNode;
    }
}
