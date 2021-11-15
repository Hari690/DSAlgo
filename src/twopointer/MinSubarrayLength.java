package twopointer;

/**
 * Given an array of positive integers nums and a positive integer target, return the minimal
 * length of a contiguous subarray [numsl,
 * numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 */
public class MinSubarrayLength {
    public int minSubArrayLen(int target, int[] nums) {
        int left=0;
        int right=0;
        int sum=0;
        int n = nums.length;
        int output=n+1;
        while(right<n) {
            sum+=nums[right++];
            while(sum>=target) {
                output=Math.min(output,right-left);
                sum-=nums[left++];
            }
        }
        return (output == n+1)?0:output;
    }
}
