package trees;

public class TwoSumBST {

    /*
        nlog(n) solution without extra memory.
     */
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        TreeNode start = root;
        TreeNode end = root;
        while (start.left != null) {
            start = start.left;
        }
        while (end.right != null) {
            end = end.right;
        }
        while (start != end) {
            int sum = start.val + end.val;
            if (sum > k) {
                end = findPredecessor(root, end);
            } else if (sum < k) {
                start = findSuccessor(root, start);
            } else {
                return true;
            }
        }
        return false;
    }
    private TreeNode findPredecessor(TreeNode root, TreeNode node) {
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < node.val) {
                pre = cur;
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return pre;
    }
    private TreeNode findSuccessor(TreeNode root, TreeNode node) {
        TreeNode succ = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > node.val) {
                succ = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return succ;
    }
}
