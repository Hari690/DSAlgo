package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * There are n oranges in the kitchen and you decided to eat some of these oranges every day as follows:
 *
 * Eat one orange.
 * If the number of remaining oranges (n) is divisible by 2 then you can eat  n/2 oranges.
 * If the number of remaining oranges (n) is divisible by 3 then you can eat  2*(n/3) oranges.
 * You can only choose one of the actions per day.
 *
 * Return the minimum number of days to eat n oranges.
 */
public class MinDaysToEatOrangesMemoize {
    // idea is to use %2 + 1 for -> getMinSteps(n/2)
    // idea is to use %3 + 1 for -> getMinSteps(n/3)
    private Map<Integer,Integer> map = new HashMap<>();
    public int minDays(int n) {
        if(n==1)
            return 1;

        if(n==0)
            return 0;

        if(n<1)
            return Integer.MAX_VALUE;

        if(map.containsKey(n))
            return map.get(n);

        map.put(n, 1+ Math.min(minDays(n/2)+n%2, minDays(n/3)+n%3));

        return map.get(n);
    }
}
