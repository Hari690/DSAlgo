package array;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
public class BuyAndSellStocks {

    public static void main(String[] args) {
        int[] prices = {7,1,0,5,3,6,4};

        new BuyAndSellStocks().maxProfit(prices);
    }

    public int maxProfit(int[] arr) {
        int n = arr.length;
        // Initialize Result
        int maxDiff = 0;

        // Initialize max element from right side
        int maxRight = arr[n-1];

        for (int i = n-2; i >= 0; i--)
        {
            if (arr[i] > maxRight)
                maxRight = arr[i];
            else
            {
                int diff = maxRight - arr[i];
                if (diff > maxDiff)
                {
                    maxDiff = diff;
                }
            }
        }
        return maxDiff;
    }
}
