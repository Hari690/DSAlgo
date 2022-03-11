package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * Example 1:
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 *
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 *
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
public class NonOverlappingIntervals {

    // keep checking for overlap and check type of overlap and
    // increase count if overlap is non-inclusive.
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length < 2) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int count = 0, last_included = 0;
        for(int i = 1; i < intervals.length; ++i){
            if(intervals[i][0] < intervals[last_included][1]){ // Overlap then check types of overlap
                count++;
                if(intervals[i][1] < intervals[last_included][1]) // small interval inside bigger one
                    last_included = i;
            } else
                last_included = i;
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = {{0,2},{1,2},{2,3}};
        NonOverlappingIntervals noi = new NonOverlappingIntervals();
        System.out.println(noi.eraseOverlapIntervals(intervals));
    }
}
