package dynamicprogramming;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount
 * of money.
 *
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins,
 * return 0.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * The answer is guaranteed to fit into a signed 32-bit integer.
 */
public class CoinChangeWays {

    // knapsack problem with infinite coins.
    public int change(int amount, int[] coins) {
        Integer[][] dp = new Integer[coins.length][amount + 1];
        return getWays(coins, amount, coins.length-1, dp);
    }

    private int getWays(int[] coins, int total, int i, Integer[][] dp) {
        if(total==0)
            return 1;

        if(total<0 || i<0)
            return 0;

        if(dp[i][total]!=null)
            return dp[i][total];

        int no = getWays(coins, total-coins[i], i, dp) + getWays(coins, total, i-1, dp);
        dp[i][total] = no;
        return no;
    }
}
