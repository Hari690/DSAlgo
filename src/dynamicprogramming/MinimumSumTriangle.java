package dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 *
 * Example 1:
 *
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * Example 2:
 *
 * Input: triangle = [[-10]]
 * Output: -10
 */
public class MinimumSumTriangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.get(triangle.size()-1).size()];
        for(int[] arr : dp)
            Arrays.fill(arr,-1);
        return findMin(0, 0, triangle, dp);
    }

    private int findMin(int row, int col, List<List<Integer>> triangle, int[][] dp) {
        if(row==triangle.size())
            return 0;

        if(col<0 || col>=triangle.get(row).size())
            return Integer.MAX_VALUE;

        if(dp[row][col]!=-1)
            return dp[row][col];

        int result =  triangle.get(row).get(col)+Math.min(findMin(row+1,col,triangle, dp), findMin(row+1,col+1,triangle, dp));
        dp[row][col] = result;
        return result;
    }
}
