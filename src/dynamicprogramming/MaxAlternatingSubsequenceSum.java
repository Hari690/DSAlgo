package dynamicprogramming;

import java.util.Arrays;

/**
 * The alternating sum of a 0-indexed array is defined as the sum of the elements at even indices minus the sum of the elements at odd
 * indices.
 *
 * For example, the alternating sum of [4,2,5,3] is (4 + 5) - (2 + 3) = 4.
 * Given an array nums, return the maximum alternating sum of any subsequence of nums (after reindexing the elements of the subsequence).
 *
 * A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without changing
 * the remaining elements' relative order. For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,
 * 4,2] is not.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,5,3]
 * Output: 7
 * Explanation: It is optimal to choose the subsequence [4,2,5] with alternating sum (4 + 5) - 2 = 7.
 * Example 2:
 *
 * Input: nums = [5,6,7,8]
 * Output: 8
 * Explanation: It is optimal to choose the subsequence [8] with alternating sum 8.
 */
public class MaxAlternatingSubsequenceSum {
    public long maxAlternatingSum(int[] nums) {
        long[][] cache = new long[nums.length][2];
        for(long[] arr: cache)
            Arrays.fill(arr, -1);
        return check(nums, 0, true, cache);
    }

    /*
        Approach is to add current value or not add(reindex)current value and recurse forward.
        While adding check both cases i.e add as even and add as odd which will create both decision trees
        and get max.
     */
    private long check(int[] nums, int index, boolean even, long[][] cache) {
        if(index==nums.length)
            return 0;

        int boolIndex = (even==true)?0:1;
        if(cache[index][boolIndex]!=-1)
            return cache[index][boolIndex];

        int sum;
        if(even)
            sum=nums[index];
        else
            sum=(-1)*nums[index];

        cache[index][boolIndex] = Math.max(sum + check(nums, index+1, !even, cache), check(nums, index+1, even, cache));
        return cache[index][boolIndex];
    }
}
