package dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount+1];

        Arrays.fill(dp, amount+1);

        dp[0] = 0;

        for(int i=1;i<=amount;i++) {
            for(int j=0;j<coins.length;j++) {
                if(coins[j]<=i) {
                    dp[i] = Math.min(dp[i],1 + dp[i-coins[j]]);
                }
            }
        }

        return (dp[amount]>amount)? -1 : dp[amount];

    }

    /*
     Dp caching is based on index and amount.
     Same approach as Coin change 2 where we try to get no of ways.
     */
    public int coinChangeTopDown(int[] coins, int amount) {
        Integer[][] dp = new Integer[coins.length][amount + 1];
        int result = recur(coins, amount, coins.length-1, dp, amount+1);
        return (result==amount+1)?-1:result;
    }

    /*
        Approach by including or not including a coin.
     */
    private int recur(int[] coins, int total, int i, Integer[][] dp, int max) {

        if(total==0)
            return 0;

        if(total<0 || i<0)
            return max;

        if(dp[i][total]!=null)
            return dp[i][total];

        int result1 = 1+recur(coins, total-coins[i], i, dp, max);
        int result2 = recur(coins, total, i-1, dp, max);

        dp[i][total] = Math.min(result1,result2);
        return dp[i][total];
    }

    /*
        Approach by iterating all coins.
     */
    public int coinChangeTopDown2(int[] coins, int amount) {
        Map<Integer,Integer> map = new HashMap<>();

        int result = recur(coins, amount, map, amount+1);
        return result==(amount+1)?-1:result;
    }

    private int recur(int[] coins, int amount, Map<Integer,Integer> cache, int max) {
        if(amount==0)
            return 0;

        if(amount<0)
            return max;

        if(cache.containsKey(amount))
            return cache.get(amount);

        int result = max;
        for(int i=0;i<coins.length;i++) {
            result = Math.min(result,1+recur(coins, amount-coins[i], cache, max));
        }

        cache.put(amount, result);
        return cache.get(amount);
    }

    public static void main(String[] args) {

        int[] coins = {1,2,5};
        System.out.println(new CoinChange().coinChangeTopDown(coins, 11));
    }

}
