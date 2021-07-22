package twopointer;

/**
 * Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product
 * of all the elements in the subarray is strictly less than k.
 */
public class SubArrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        int cnt = 0;
        int pro = 1;
        for (int i = 0, j = 0; j < nums.length; j++) {
            pro *= nums[j];
            while (i <= j && pro >= k) {
                pro /= nums[i++];
            }
            // Each step introduces x new subarrays, where x is the size of the current window (j + 1 - i);
            cnt += j - i + 1;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int nums[] = {10,5,2,6};
        SubArrayProductLessThanK subArrayProductLessThanK = new SubArrayProductLessThanK();
        System.out.println(subArrayProductLessThanK.numSubarrayProductLessThanK(nums,100));
    }
}
