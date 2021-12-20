package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array
 * days. Each day is an integer from 1 to 365.
 *
 * Train tickets are sold in three different ways:
 *
 * a 1-day pass is sold for costs[0] dollars,
 * a 7-day pass is sold for costs[1] dollars, and
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 *
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 */
public class MinCostForTickets {

    public int mincostTickets(int[] days, int[] costs) {
        return minDollars(days, costs, 0, new HashMap<>());
    }

    private int minDollars(int[] days, int[] costs,int i, Map<Integer,Integer> map) {
        if(i==days.length) {
            return 0;
        }

        if(map.containsKey(i))
            return map.get(i);

        int min = Integer.MAX_VALUE;
        min = Math.min(min, costs[0]+minDollars(days, costs, getNextIndex(days, i, 1), map));
        min = Math.min(min, costs[1]+minDollars(days, costs, getNextIndex(days, i, 7), map));
        min = Math.min(min, costs[2]+minDollars(days, costs, getNextIndex(days, i, 30), map));

        map.put(i, min);

        return min;
    }

    private int getNextIndex(int[] days, int index, int distance) {
        distance+=days[index];
        int i=index+1;

        for(;i<days.length;i++) {
            if(days[i]>=distance)
                return i;
        }
        return i;
    }
}
