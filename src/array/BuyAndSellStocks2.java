package array;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 */
public class BuyAndSellStocks2 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n < 2) return 0;

        int p1 = prices[0], p2 = prices[n-1];
        int[] profit1 = new int[n];
        int[] profit2 = new int[n];

        for(int i = 1; i < n; ++i){
            profit1[i] = Math.max(profit1[i-1], prices[i] - p1);
            p1 = Math.min(p1, prices[i]);

            int j = n-1-i;
            profit2[j] = Math.max(profit2[j+1], p2 - prices[j]);
            p2 = Math.max(p2, prices[j]);
        }

        int profit = 0;
        for(int i = 0; i < n; ++i)
            profit = Math.max(profit, profit1[i] + profit2[i]);
        return profit;
    }
}
