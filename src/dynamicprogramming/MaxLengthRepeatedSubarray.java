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
    public int findLength(int[] A, int[] B) {
        if(A == null||B == null) return 0;
        int m = A.length;
        int n = B.length;
        int max = 0;
        //dp[i][j] is the length of longest common subarray ending with nums[i] and nums[j]
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0;i <= m;i++){
            for(int j = 0;j <= n;j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
                else{
                    if(A[i - 1] == B[j - 1]){
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                        max = Math.max(max,dp[i][j]);
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {0, 0, 0, 0, 1};
        int[] nums2 = new int[] {1, 0, 0, 0, 0};
        MaxLengthRepeatedSubarray solution = new MaxLengthRepeatedSubarray();
        solution.findLength(nums1, nums2);
    }
}
