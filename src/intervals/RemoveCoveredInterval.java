package intervals;

import java.util.Arrays;

/**
 * Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that are covered by another interval in the list.
 * The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
 * Return the number of remaining intervals.
 *
 * Example 1:
 *
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 * Example 2:
 *
 * Input: intervals = [[1,4],[2,3]]
 * Output: 1
 */
public class RemoveCoveredInterval {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->{
            if(a[0]!=b[0])
                return a[0]-b[0];
            else
                return b[1]-a[1];
        });

        int current=intervals[0][1];
        int count=1;
        for(int i=1;i<intervals.length;i++) {
            int[] interval = intervals[i];
            if(!(interval[1]<=current))
                count++;
            current=Math.max(current,interval[1]);
        }
        return count;
    }
}
