package trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 * Example 1:
 * Input: n = 3
 * Output: 5
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 */
public class UniqueBST {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        return calculate(n, dp);
    }

    private int calculate(int n, int[] dp) {
        if(n<=1)
            return 1;

        if(dp[n]!=0)
            return dp[n];

        dp[n]=0;
        for(int i=1;i<=n;i++) {
            dp[n]+=calculate(i-1, dp)*calculate(n-i, dp);
        }
        return dp[n];
    }

    /*
        Given an integer n, return all the structurally unique BST's (binary search trees),
        which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
        Example 1:
        Input: n = 3
        Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
        Example 2:
        Input: n = 1
        Output: [[1]]

        O(2^n)
     */

    Map<String,List<TreeNode>> map = new HashMap<>();
    public List<TreeNode> generateTrees(int n) {
        if(n==0) return new LinkedList();
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int s, int e) {
        List<TreeNode> res = new LinkedList<>();
        if (s > e) {
            res.add(null); // empty tree
            return res;
        }

        if(map.containsKey(s+" "+e))
            return map.get(s+" "+e);

        for (int i = s; i <= e; ++i) {
            List<TreeNode> leftTrees = generateTrees(s, i - 1);
            List<TreeNode> righTrees = generateTrees(i + 1, e);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : righTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        map.put(s+" "+e, res);
        return res;
    }

    public static void main(String[] args) {
        UniqueBST uniqueBST = new UniqueBST();
        List<TreeNode> result = uniqueBST.generateTrees(2);
        result.forEach(System.out::println);
    }
}
