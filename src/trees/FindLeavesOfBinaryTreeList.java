package trees;

import java.util.ArrayList;
import java.util.List;

/**
 *  Find Leaves of Binary Tree
 *  Given a binary tree, collect a tree's nodes as if you were doing this:
 *  Collect and remove all leaves, repeat until the tree is empty.
 *
 *  Example:
 *
 *  Input: [1,2,3,4,5]
 *
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 *
 *  Output: [[4,5,3],[2],[1]]
 *
 *  Explanation:
 *
 *  1. Removing the leaves [4,5,3] would result in this tree:
 *
 *    1
 *   /
 *  2
 *
 *
 *  2. Now removing the leaf [2] would result in this tree:
 *
 *  1
 *
 *
 *  3. Now removing the leaf [1] would result in the empty tree:
 *
 *  []
 */
public class FindLeavesOfBinaryTreeList {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, root);
        return result;
    }

    // traverse the tree bottom-up recursively
    private int helper(List<List<Integer>> list, TreeNode root) {
        if (root == null) {
            return -1; // @note: +1==0, mapping to list index
        }

        int left = helper(list, root.left);
        int right = helper(list, root.right);
        int currentDepthFromBottom = Math.max(left, right) + 1;

        // the first time this code is reached is when curr==0,
        // since the tree is bottom-up processed.
        if (list.size() <= currentDepthFromBottom) {
            list.add(new ArrayList<Integer>());
        }

        list.get(currentDepthFromBottom).add(root.val);

        return currentDepthFromBottom;
    }
}
