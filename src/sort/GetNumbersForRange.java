package sort;

import java.util.Arrays;

/**
 * Given some numbers [3,5,7,8,10], and interval list : [1,6,15] , they wanted to know how many numbers occur between each interval . The answer for this example will be : [2,3] i.e [1,6] -> [3,5] and [6,15] -> [7,8,10].
 * I clarified the interval assumptions. the boundaries inclusivity & exclusivity. I gave a binary search related answer, after sorting out the numbers. Collaborated perfectly during the entire time.
 */
public class GetNumbersForRange {
    public static int[] getNumbersForRange(int[] nums, int[] intervals) {
        Arrays.sort(nums); //if numbers array is unsorted
        int[] result = new int[intervals.length-1];
        int bound = 1;
        for(int num : nums) {
            while(bound < intervals.length && num > intervals[bound]) ++bound;
            if(bound >= intervals.length) break;
            result[bound-1]++;
        }
        return result;
    }
}
