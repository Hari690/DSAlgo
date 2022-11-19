package trees;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 *
 *
 */
public class HouseRobber3MaxWithoutConsecutiveFromTree {
    public int rob(TreeNode root) {
        int[] result = dfs(root);
        return Math.max(result[0],result[1]);
    }

    private int[] dfs(TreeNode root) {
        if(root==null)
            return new int[]{0,0};

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int withRoot = root.val+left[1]+right[1];
        int withoutRoot = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);

        return new int[]{withRoot, withoutRoot};
    }
}
