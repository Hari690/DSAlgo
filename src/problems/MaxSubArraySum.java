package problems;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 */
public class MaxSubArraySum {

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        new MaxSubArraySum().maxSubArray(nums);

    }

    public int maxSubArray(int[] nums) {

        int[] sum = new int[nums.length];

        if(nums.length>0) {
            sum[0] = nums[0];
        }

        int max = Integer.MIN_VALUE;
        for(int i=1;i<nums.length;i++) {
            if(sum[i-1]+nums[i]>nums[i]) {
                sum[i]=sum[i-1]+nums[i];
            } else {
                sum[i]=nums[i];
            }
            max = Math.max(sum[i], max);
        }
        return max;

    }
}
