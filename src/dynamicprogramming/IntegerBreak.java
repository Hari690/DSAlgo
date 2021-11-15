package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
 *
 * Return the maximum product you can get.
 */
public class IntegerBreak {
    public int integerBreakTopDown(int n) {
        // 1.Init except dp[n], since it cannot be itself and must break into two positive
        int[] maxProd = new int[n + 1];
        for (int i = 1; i < n; i++) {
            maxProd[i] = i;
        }

        // 2.Compute max product
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - j; j++) { // Note: only check j <= i - j
                maxProd[i] = Math.max(maxProd[i], maxProd[j] * maxProd[i - j]);
            }
        }
        return maxProd[n];
    }

    private Map<Integer,Integer> map = new HashMap<>();
    public int integerBreakBottomUp(int n) {
        if(n<=2)
            return 1;

        if(map.containsKey(n))
            return map.get(n);

        int max=1;
        for(int i=2;i<n;i++) {
            int first = i;
            int second = n-i;
            int product = first*Math.max(second,integerBreakBottomUp(second));
            max = Math.max(max, product);
        }
        map.put(n, max);
        return max;
    }
}
