package dynamicprogramming;

/**
 * Problem Statement:
 * Find the number of possible different combinations to fill a 4 x N area (4 units height and N units width, N ≥ 1) with tiles of dimention 4 x 1 (4 units height and 1 unit width).
 *
 * For example:
 * if N = 5, answer is 3
 */
public class TilingProblem {

    public int findWays(int n) {
        int[] dp = new int[n];
        dp[0] = dp[1]= dp[2]=dp[3]= 1;
        dp[4] = 2; //starting 4rth position we start filling horizontal tiles , so dp[4] = 2
        for(int i = 5; i <=n; i++)
            dp[i] = dp[i-1] + dp[i-4];

        // in case of 2*n dominos this will be dp[i] = dp[i-1] + dp[i-2]
        return dp[n-1];
    }
}
