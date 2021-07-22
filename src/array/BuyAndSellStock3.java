package array;

public class BuyAndSellStock3 {
    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of
     * the stock multiple times).
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        // any number of transactions
        int maxprofit=0;
        if(prices.length<=1)
            return 0;
        for(int i=0;i<prices.length-1;i++)
        {
            if(prices[i+1]>prices[i])
                maxprofit+=prices[i+1]-prices[i];
        }
        return maxprofit;

    }
}
