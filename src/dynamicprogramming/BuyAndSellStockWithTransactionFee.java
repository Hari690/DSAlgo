package dynamicprogramming;

/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 * Medium
 *
 * 3661
 *
 * 97
 *
 * Add to List
 *
 * Share
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Example 2:
 *
 * Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 */
public class BuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        int profit =  findProfit(prices, 0, fee, true, dp);
        return profit;
    }

    private int findProfit(int[] prices, int index, int fee, boolean buying, int[][] dp) {
        if(index>=prices.length)
            return 0;
        if(dp[index][(buying==true)?0:1]!=0)
            return dp[index][(buying==true)?0:1];
        if (buying) {
            int sum1 = findProfit(prices, index+1, fee, false, dp) - prices[index];
            int sum2 = findProfit(prices, index+1, fee, true, dp);
            dp[index][0] = Math.max(sum1, sum2);
            return dp[index][0];
        } else {
            int sum1 = findProfit(prices, index+1, fee, true, dp) + prices[index] - fee;
            int sum2 = findProfit(prices, index+1, fee, false, dp);
            dp[index][1] = Math.max(sum1, sum2);
            return dp[index][1];
        }
    }

    public static void main(String[] args) {
        BuyAndSellStockWithTransactionFee solution = new BuyAndSellStockWithTransactionFee();
        int[] profit = {1,3,2,8,4,9};
        solution.maxProfit(profit, 2);
    }
}
