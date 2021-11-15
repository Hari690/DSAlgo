package treemap;

import java.util.Map;
import java.util.TreeMap;

/**
 * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
 * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized.
 * Return an array of right interval indices for each interval i. If no right interval exists for interval i,
 * then put -1 at index i.
 * Input: intervals = [[3,4],[2,3],[1,2]]
 * Output: [-1,0,1]
 */
public class FindRightInterval {
    public int[] findRightInterval(int[][] intervals) {

        int[] result = new int[intervals.length];
        java.util.NavigableMap<Integer, Integer> intervalMap = new TreeMap<>();

        for (int i = 0; i < intervals.length; ++i) {
            intervalMap.put(intervals[i][0], i);
        }

        for (int i = 0; i < intervals.length; ++i) {
            Map.Entry<Integer, Integer> entry = intervalMap.ceilingEntry(intervals[i][1]);
            result[i] = (entry != null) ? entry.getValue() : -1;
        }

        return result;

    }
}
