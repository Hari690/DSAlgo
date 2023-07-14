package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
 * A subsequence is a sequence that can be derived from arr by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: arr = [1,2,3,4], difference = 1
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [1,2,3,4].
 *
 * Example 2:
 * Input: arr = [1,3,5,7], difference = 1
 * Output: 1
 * Explanation: The longest arithmetic subsequence is any single element.
 *
 * Example 3:
 * Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [7,5,3,1].
 *
 *
 * Constraints:
 * 1 <= arr.length <= 105
 * -104 <= arr[i], difference <= 104
 */
public class LargestArithmeticProgressionSequenceOfConstantDifferenceDpWithMap {
    public int longestSubsequence(int[] a, int d) {
        // O(n^2)
//        int maxCount = 1;
//        for(int i=0;i<a.length;i++) {
//            int count = 1;
//            for(int j=i+1;j<a.length;j++) {
//                if(a[j]-a[i]==count*d) {
//                    count++;
//                }
//            }
//            maxCount = Math.max(count, maxCount);
//        }
//        return maxCount;

        Map<Integer, Integer> cache = new HashMap<>();
        int maxCount = 1;
        for (int i = 0; i < a.length; i++) {
            int check = a[i] - d;
            int count = 1;
            if (cache.containsKey(check)) {
                count += cache.get(check);
            }
            cache.put(a[i], count);
            maxCount = Math.max(count, maxCount);
        }
        return maxCount;
    }

    public static void main(String[] args) {
        LargestArithmeticProgressionSequenceOfConstantDifferenceDpWithMap solution = new LargestArithmeticProgressionSequenceOfConstantDifferenceDpWithMap();
        int[] arr = {1,2,3,4};
        solution.longestSubsequence(arr, 1);
    }
}
