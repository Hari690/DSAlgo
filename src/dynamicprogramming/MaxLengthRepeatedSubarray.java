package dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 * Example 2:
 *
 * Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * Output: 5
 */
public class MaxLengthRepeatedSubarray {

    // Difference between this way and subsequence is here we use param to incrmement and unset count if there is a match
    // and use same variable to set max count for a call.
    public int findLength(int[] nums1, int[] nums2) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        return check(nums1, nums2, 0, 0, 0, map);
    }

    private int check(int[] nums1, int[] nums2, int i, int j, int count, Map<String,Integer> map) {
        if(i==nums1.length || j==nums2.length)
            return count;

        String key = i+" "+j+" "+count;
        if(map.get(key)!=null)
            return map.get(key);
        int count1 = count;
        if(nums1[i]==nums2[j]) {
            count1 = check(nums1, nums2, i+1, j+1, count+1, map);
        }
        int count2 = check(nums1, nums2, i+1, j, 0, map);
        int count3 = check(nums1, nums2, i, j+1, 0, map);
        int result = Math.max(count1,Math.max(count2,count3));
        map.put(key,result);
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {0, 0, 0, 0, 1};
        int[] nums2 = new int[] {1, 0, 0, 0, 0};
        MaxLengthRepeatedSubarray solution = new MaxLengthRepeatedSubarray();
        solution.findLength(nums1, nums2);
    }
}
