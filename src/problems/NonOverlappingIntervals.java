package problems;

import java.util.Arrays;

public class NonOverlappingIntervals {
    // keep checking for overlap and check type of overlap and
    // increase count if overlap is non-inclusive.
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length < 2) return 0;
        // sort intervals by order of starting time of intervals.
        Arrays.sort(intervals, (a, b)->(a[0] - b[0]));
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
}
