package dynamicprogramming;

/**
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 *
 * It is guaranteed that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 */
public class MaxProductSubarray {

    public static void main(String[] args) {
        int a[] = {1,-2,-1,4};
        new MaxProductSubarray().maxProduct(a);
    }

    public int maxProduct(int[] nums) {
        int totalMax = nums[0];
        int max = nums[0];
        int min = nums[0];

        for(int i=1; i<nums.length; i++) {
            //take max until now
            int tmp = max;
            // -1,8 so we need to check num[i]
            max = Math.max(nums[i], Math.max(max*nums[i], min*nums[i]));
            min = Math.min(nums[i], Math.min(min*nums[i], tmp*nums[i]));
            totalMax = Math.max(max, totalMax);
        }
        return totalMax;
    }
}
