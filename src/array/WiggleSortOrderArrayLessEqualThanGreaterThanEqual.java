package array;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *  For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */
public class WiggleSortOrderArrayLessEqualThanGreaterThanEqual {
    /*
        One option is to sort and do swapping but not really necessary here since we do this to maintain order with only previous element.
        When i is odd, nums[i] >= nums[i-1]
        When i is an even number, nums[i] <= nums[i-1]
        According to its parity, compare it with its corresponding condition, and if it doesn’t match, just swap the position with the previous number.
     */
    public void wiggleSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        for (int i = 1; i < nums.length; ++i) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1]))
                swap(nums, i, i - 1);
            if ((i % 2 == 0 && nums[i] > nums[i - 1]))
                swap(nums, i, i - 1);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /*
        No equality condition
        eg - check for [5,5,5,5,4,4,4,4]
     */
    public void wiggleSort2(int[] nums) {
        int[] tmp = new int[nums.length];
        Arrays.sort(nums);
        int left = (nums.length - 1) / 2, right = nums.length - 1;
        for (int i = 0; i < tmp.length; i ++) {
            if (i % 2 == 0) {
                tmp[i] = nums[left--];
            }
            else {
                tmp[i] = nums[right--];
            }
        }
        for (int i = 0; i < tmp.length; i ++) {
            nums[i] = tmp[i];
        }
    }
}
