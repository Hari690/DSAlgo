package dynamicprogramming;

/*
In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will
travel is given as an array days.  Each day is an integer from 1 to 365.

Train tickets are sold in 3 different ways:

a 1-day pass is sold for costs[0] dollars;
a 7-day pass is sold for costs[1] dollars;
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

Return the minimum number of dollars you need to travel every day in the given list of days.
 */
public class MinCostTickets {
    public int minCostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n+1];
        for(int i = 0; i < n; ++i) dp[i] = 365*costs[0];

        for(int i = n-1; i >= 0; --i){
            int d7 = i, d30 = i;
            while(d7 < n && days[d7] < days[i] + 7) ++d7;
            while(d30 < n && days[d30] < days[i] + 30) ++d30;
            dp[i] = Math.min(costs[0] + dp[i+1], Math.min(costs[1] + dp[d7], costs[2] + dp[d30]));
        }
        return dp[0];
    }
}
