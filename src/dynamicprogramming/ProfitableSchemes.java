package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * There is a group of n members, and a list of various crimes they could commit. The ith crime generates a profit[i] and requires group[i] members to participate in it. If a member participates in one crime, that member can't participate in another crime.
 * Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit, and the total number of members participating in that subset of crimes is at most n.
 * Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 109 + 7.
 *
 * Example 1:
 * Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * Output: 2
 * Explanation: To make a profit of at least 3, the group could either commit crimes 0 and 1, or just crime 1.
 * In total, there are 2 schemes.
 *
 * Example 2:
 * Input: n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * Output: 7
 * Explanation: To make a profit of at least 5, the group could commit any crimes, as long as they commit one.
 * There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
 */
public class ProfitableSchemes {

    /*  Cases are take the value and not take the value and keep recursing. There will not be any duplicates in this case.
        If we get profit we want by the last index then add to count. Cache it with dp.
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        Map<String,Integer> cache = new HashMap<>();
        return getWays(0, 0, minProfit, group, profit, n, cache);
    }

    private int getWays(int index, int sum, int minProfit, int[] group, int[] profit, int n,Map<String,Integer> cache) {
        if(index==group.length)
            return (sum>=minProfit)?1:0;

        if(cache.containsKey(index+" "+n+" "+sum))
            return cache.get(index+" "+n+" "+sum);

        int result = 0;
        result+=getWays(index+1, sum, minProfit, group, profit, n, cache);
        if(n-group[index]>=0)
            result+=getWays(index+1, sum+profit[index], minProfit, group, profit, n-group[index], cache);
        cache.put(index+" "+n+" "+sum,result);
        return result;
    }
}
