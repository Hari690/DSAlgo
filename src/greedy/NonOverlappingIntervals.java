package greedy;

import java.util.Arrays;

public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length < 2) return 0;
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

    public static void main(String[] args) {
        int[][] intervals = {{0,2},{1,2},{2,3}};
        NonOverlappingIntervals noi = new NonOverlappingIntervals();
        System.out.println(noi.eraseOverlapIntervals(intervals));
    }
}
