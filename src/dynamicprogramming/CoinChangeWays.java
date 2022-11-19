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
 *
 * Example 1:
 *
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 *
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 *
 * Input: amount = 10, coins = [10]
 * Output: 1
 */
public class CoinChangeWays {

    // knapsack problem with infinite coins.
    public int change(int amount, int[] coins) {
        Integer[][] dp = new Integer[coins.length][amount + 1];
        return getWays(coins, amount, coins.length-1, dp);
    }

    /*
        If we iterate all coins as in Coin change 1 then we would end up with duplicates(1,1,2) & (2,1,1).
        So to avoid them we can use knapsack solution where we can do 2^n by doing 2 cases where we add
        an element and don't add an element in one direction.
        We can cache those values based on amount and index.
     */
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

    public static void main(String[] args) {
        CoinChangeWays coinChangeWays = new CoinChangeWays();
        int[] coins = {1,2,5};
        coinChangeWays.change(5, coins);
    }
}
