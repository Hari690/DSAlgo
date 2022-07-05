package sort;

import java.util.Arrays;

/**
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 * In one move, you can increment or decrement an element of the array by 1.
 * Test cases are designed so that the answer will fit in a 32-bit integer.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element):
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * Example 2:
 * Input: nums = [1,10,2,9]
 * Output: 16
 */
public class MinMovesMakeArrayElementsEqual2 {
    public int minMoves2(int[] nums) {
        int median = findMedian(nums);

        long diff=0;
        for(int num : nums) {
            diff+=Math.abs(num-median);
        }
        return (int)diff;
    }

    private int findMedian(int[] nums) {
        Arrays.sort(nums);
        if(nums.length%2==0)
            return (nums[nums.length/2]+nums[nums.length/2-1])/2;
        else
            return nums[nums.length/2];
    }
}
