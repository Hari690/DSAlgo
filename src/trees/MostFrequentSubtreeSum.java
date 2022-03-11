package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given the root of a binary tree, return the most frequent subtree sum.
 * If there is a tie, return all the values with the highest frequency in any order.
 *
 * The subtree sum of a node is defined as the sum of all the node values formed by the
 * subtree rooted at that node (including the node itself).
 */
public class MostFrequentSubtreeSum {
    private Map<Integer,Integer> map = new HashMap<>();
    private int max=0;
    public int[] findFrequentTreeSum(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root);
        for(Map.Entry<Integer,Integer> entry: map.entrySet()) {
            if(entry.getValue()==max) {
                result.add(entry.getKey());
            }
        }
        int[] output = new int[result.size()];
        int i=0;
        for(int r : result)
            output[i++]=r;
        return output;
    }

    private int dfs(TreeNode root) {
        if(root==null)
            return 0;

        int sum = dfs(root.left) + dfs(root.right) + root.val;
        map.put(sum, map.getOrDefault(sum,0)+1);
        max = Math.max(max, map.get(sum));
        return sum;
    }
}
