package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 */
public class InsertIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        /*
            Do merging only when we encounter the newInterval after sorting.
            Other option is to create new array with newInterval and merge everything together but that's O(nlogn) and O(n) space.
         */
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        int i=0;
        List<int[]> allIntervals = new ArrayList<>();
        while(i<intervals.length && intervals[i][1]<newInterval[0]) {
            allIntervals.add(intervals[i++]);
        }

        while(i<intervals.length && intervals[i][0]<=newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0],newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1],newInterval[1]);
            i++;
        }
        allIntervals.add(newInterval);

        while(i<intervals.length) {
            allIntervals.add(intervals[i++]);
        }

        return allIntervals.toArray(new int[allIntervals.size()][]);
    }
}
