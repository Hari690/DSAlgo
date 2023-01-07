package dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the
 * stock multiple times) with the following restrictions:
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 */
public class BuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        if(prices.length <= 1) return 0;
        int A = 0, B = -prices[0], C = 0;
        for(int i = 1; i < prices.length; ++i){
            int tmp = A;
            // previous sale or hold
            A = Math.max(A, C);
            // sell
            C = B + prices[i];
            // buy or hold
            B = Math.max(B, tmp - prices[i]);
        }
        return Math.max(A, C);
    }

    public int maxProfitDp(int[] prices) {
        int[][] dp = new int[prices.length][2];
        for(int arr[]: dp)
            Arrays.fill(arr, -1);
        return findMax(prices, 0, true, dp);
    }

    private int findMax(int[] profit, int index, boolean buying, int[][] dp) {
        if(index>=profit.length)
            return 0;

        if(dp[index][buying?1:0]!=-1)
            return dp[index][buying?1:0];

        if(buying) {
            int result = Math.max(-profit[index]+findMax(profit,index+1,!buying, dp),
                    findMax(profit,index+1,buying, dp));
            dp[index][buying?1:0] = result;
        } else {
            int result = Math.max(profit[index]+findMax(profit,index+2,!buying, dp),
                    findMax(profit,index+1,buying, dp));
            dp[index][buying?1:0] = result;
        }
        return dp[index][buying?1:0];
    }
}
