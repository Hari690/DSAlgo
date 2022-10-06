package dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You have n dice and each die has k faces numbered from 1 to k.
 * Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.
 *
 * Example 1:
 * Input: n = 1, k = 6, target = 3
 * Output: 1
 * Explanation: You throw one die with 6 faces.
 * There is only one way to get a sum of 3.
 * Example 2:
 * Input: n = 2, k = 6, target = 7
 * Output: 6
 * Explanation: You throw two dice, each with 6 faces.
 * There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 * Example 3:
 * Input: n = 30, k = 30, target = 500
 * Output: 222616187
 * Explanation: The answer must be returned modulo 109 + 7.
 */
public class NumberDiceRollsTargetSum {
    int MOD = 1000000007;
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n+1][target+1];
        for(int i=0;i<n+1;i++)
            Arrays.fill(dp[i], -1);
        return backtrack(n,k,target,0,0, dp);
    }

    private int backtrack(int n, int k, int target, int sum, int no, int[][] dp) {
        if(no==n && sum==target)
            return 1;
        if(no>=n || sum>=target)
            return 0;
        if(dp[no][sum]!=-1)
            return dp[no][sum];
        int total=0;
        for(int i=1;i<=k;i++) {
            total=(total+backtrack(n, k, target, sum+i,no+1, dp))%MOD;
        }
        dp[no][sum] = total;
        return total;
    }

    public static void main(String[] args) {
        NumberDiceRollsTargetSum numberDiceRollsTargetSum = new NumberDiceRollsTargetSum();

        System.out.println(numberDiceRollsTargetSum.numRollsToTarget(1,6,3));
    }
}
