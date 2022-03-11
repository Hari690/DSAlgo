package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 *
 *
 */
public class InsertIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {
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
