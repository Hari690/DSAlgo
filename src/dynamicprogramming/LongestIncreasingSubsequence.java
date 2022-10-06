package dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the
 * remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;
        for(int i=0;i<nums.length;i++) {
            for(int j=i+1;j<nums.length;j++) {
                if(nums[j]>nums[i]) {
                    dp[j]=Math.max(dp[j],dp[i]+1);
                    max = Math.max(max, dp[j]);
                }
            }
        }

        return max+1;
    }

    public int maxEnvelopesAlt(int[][] envelopes) {
        Arrays.sort(envelopes, Comparator.comparingInt((int[] envelope) -> envelope[0]));
        return lengthOfLIS(envelopes);

    }

    public int lengthOfLIS(int[][] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int max = 1;
        for( int i=0;i<nums.length;i++) {
            for( int j=i+1;j<nums.length;j++) {
                if ( nums[j][0]>nums[i][0] && nums[j][1]>nums[i][1]) {
                    dp[j] = Math.max(dp[j],dp[i]+1);
                    max=Math.max(max,dp[j]);
                }
            }
        }
        return max;
    }
}
