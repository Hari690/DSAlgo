package twopointer;

import java.util.Arrays;

/**
 * You are given an array of integers nums and an integer target.
 *
 * Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target. Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,5,6,7], target = 9
 * Output: 4
 * Explanation: There are 4 subsequences that satisfy the condition.
 * [3] -> Min value + max value <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 * Example 2:
 *
 * Input: nums = [3,3,6,8], target = 10
 * Output: 6
 * Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 */
public class SubsequenceWithMinMaxLessThanValue {
    public int numSubseq(int[] nums, int target) {
        int count = 0;
        int mod = (int)Math.pow(10,9)+7;

        Arrays.sort(nums);
        int right = nums.length-1;
        for(int left=0;left<nums.length;left++) {
            while(nums[left]+nums[right]>target && left<=right) {
                right--;
            }
            if(left<=right) {
                System.out.println(right-left);
                count+=Math.pow(2,right-left);
                //count=count%mod;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        SubsequenceWithMinMaxLessThanValue solution = new SubsequenceWithMinMaxLessThanValue();
        int[] arr = new int[]{3, 3, 6, 8};
        solution.numSubseq(arr, 10);
    }
}
