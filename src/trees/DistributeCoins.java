package trees;

/**
 * You are given the root of a binary tree with n nodes where each node in the tree has node.val coins and there are n coins total.
 * In one move, we may choose two adjacent nodes and move one coin from one node to another. (A move may be from parent to child, or from
 * child to parent.)
 * Return the number of moves required to make every node have exactly one coin.
 */
public class DistributeCoins {
    int res = 0;
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left), right = dfs(root.right);
        res += Math.abs(left) + Math.abs(right);
        return root.val + left + right - 1;
    }
}
