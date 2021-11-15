package dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
 *
 * One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width
 * and height.
 *
 * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
 *
 */
public class MaxEnvelopes {

    /*
        LIS on 2-d sorted array.
     */
    public int maxEnvelopes(int[][] envelopes) {
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

    public static void main(String[] args) {
        MaxEnvelopes maxEnvelopes = new MaxEnvelopes();
        int[][] nums = {{5,4},{6,4},{6,7},{2,3}};
        maxEnvelopes.maxEnvelopes(nums);
    }
}
