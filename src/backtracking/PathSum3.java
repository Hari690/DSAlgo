package backtracking;

import trees.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals
 * targetSum.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to
 * child nodes).
 */
public class PathSum3 {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }

    public int helper(TreeNode root, int currSum, int target, Map<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }

        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        //visit
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        //unvisit
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }
}
