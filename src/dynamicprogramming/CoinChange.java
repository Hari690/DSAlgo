package dynamicprogramming;

import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 */
public class CoinChange {

    public static void main(String[] args) {

        int[] coins = {1,2,5};
        System.out.println(new CoinChange().coinChange(coins, 11));
    }

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
}
