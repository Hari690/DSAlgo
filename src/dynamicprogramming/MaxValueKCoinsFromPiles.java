package dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 * There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted denominations.
 * In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.
 * Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom,
 * and a positive integer k, return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally.
 *
 * Example 1:
 * Input: piles = [[1,100,3],[7,8,9]], k = 2
 * Output: 101
 * Explanation:
 * The above diagram shows the different ways we can choose k coins.
 * The maximum total we can obtain is 101.
 *
 * Example 2:
 * Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
 * Output: 706
 * Explanation:
 * The maximum total can be obtained if we choose all coins from the last pile.
 *
 *
 */
public class MaxValueKCoinsFromPiles {
    /*
        Condense to 2d dp by maintaining pileIndex we are picking from and coins left and running a for loop in each method call
        to see how many we can pick from current pile by comparing with if we need to pick from current pile or next pile for each coin
        until we run out of coins.

        Time complexity - O(P*k) where P is total no of coins i.e size of 2d matrix.
     */
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[][] cache = new int[piles.size()][k+1];
        for(int i=0;i<piles.size();i++) {
            Arrays.fill(cache[i], -1);
        }
        return dfs(piles, 0, k, cache);
    }

    private int dfs(List<List<Integer>> piles, int pileIndex, int coinsLeft, int[][] cache) {
        if(pileIndex==piles.size()) {
            return 0;
        }

        if(cache[pileIndex][coinsLeft]!=-1)
            return cache[pileIndex][coinsLeft];

        // skip pile altogether
        int result = dfs(piles, pileIndex+1, coinsLeft, cache);
        int currPile = 0;
        for(int i=0;i<Math.min(coinsLeft,piles.get(pileIndex).size());i++) {
            currPile+=piles.get(pileIndex).get(i);
            int next = dfs(piles, pileIndex+1, coinsLeft-1-i, cache);
            result = Math.max(result, currPile+next);
        }

        cache[pileIndex][coinsLeft] = result;

        return result;
    }

    public static void main(String[] args) {
        MaxValueKCoinsFromPiles maxValueKCoinsFromPiles = new MaxValueKCoinsFromPiles();
        List<List<Integer>> list = List.of(List.of(7),List.of(7),List.of(1,1,700));
        System.out.println(maxValueKCoinsFromPiles.maxValueOfCoins(list, 3));
    }
}
