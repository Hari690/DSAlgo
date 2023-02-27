package dynamicprogramming;

import java.util.Arrays;

/**
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 * We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the stones have weights x and y with x <= y. The result of this smash is:
 *
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 *
 * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
 * Example 1:
 * Input: stones = [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0, so the array converts to [1], then that's the optimal value.
 *
 * Example 2:
 * Input: stones = [31,26,33,21,40]
 * Output: 5
 */
public class LastStoneWeight2MinDifferenceBetweenSumOfArrayDividedIntoTwo {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for(int stone : stones)
            sum+=stone;

        int halfSum = sum/2 + ((sum%2==1)?1:0);
        int[][] dp = new int[halfSum][stones.length];
        for(int i=0;i<dp.length;i++)
            Arrays.fill(dp[i], -1);

        return getBreakPoint(stones, 0,0, halfSum, sum, dp);
    }

    private int getBreakPoint(int[] stones, int i, int sum, int totalSum, int finalSum, int[][] dp) {
        if(i==stones.length || sum>=totalSum) {
            return Math.abs(sum-(finalSum-sum));
        }

        if(dp[sum][i]!=-1)
            return dp[sum][i];

        dp[sum][i] = Math.min(getBreakPoint(stones, i+1, sum, totalSum, finalSum, dp),getBreakPoint(stones, i+1, sum+stones[i], totalSum,finalSum, dp));
        return dp[sum][i];
    }

    public static void main(String[] args) {
        LastStoneWeight2MinDifferenceBetweenSumOfArrayDividedIntoTwo lastStoneWeight2 = new LastStoneWeight2MinDifferenceBetweenSumOfArrayDividedIntoTwo();

        lastStoneWeight2.lastStoneWeightII(new int[]{2,7,4,1,8,1});
    }
}
