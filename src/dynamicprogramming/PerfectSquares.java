package dynamicprogramming;

import java.util.Arrays;

/**
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For
 * example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 */
public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        dp[1]=1;

        int j;
        for(int i=2;i<=n;i++) {
            j=1;
            while((i-j*j)>=0) {
                dp[i] = Math.min(dp[i],dp[i-j*j]+1);
                j++;
            }
        }

        return dp[n];
    }
}
