package dynamicprogramming;

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

    public int maxprofitDp(int[] prices) {
        Map<String,Integer> cache = new HashMap<>();
        return maxprofit(prices, 0 , true, cache);
    }

    private int maxprofit(int[] prices, int index, boolean buying, Map<String,Integer> cache) {
        if(index>=prices.length) {
            return 0;
        }
        if(cache.containsKey(index +String.valueOf(buying)))
            return cache.get(index +String.valueOf(buying));
        if(buying) {
            // can only buy or cooldown.
            int buy = maxprofit(prices, index+1, !buying, cache)-prices[index];
            int cooldown = maxprofit(prices, index+1, buying, cache);
            cache.put(index +String.valueOf(buying),Math.max(buy, cooldown));
            return cache.get(index +String.valueOf(buying));
            // can sell with mandatory cooldown or stay in cooldown
        } else {
            int sell = maxprofit(prices, index+2, !buying, cache)+prices[index];
            int cooldown = maxprofit(prices, index+1, buying, cache);
            cache.put(index +String.valueOf(buying),Math.max(sell, cooldown));
            return cache.get(index +String.valueOf(buying));
        }
    }
}
