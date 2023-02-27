package binarysearch;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 * Return the single element that appears only once.
 * Your solution must run in O(log n) time and O(1) space.
 * Example 1:
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 */
public class SingleElementSortedArray {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length-1;

        if(nums.length==1)
            return nums[0];

        if(nums[0]!=nums[1])
            return nums[0];

        if(nums[nums.length-1]!=nums[nums.length-2])
            return nums[nums.length-1];

        int mid = 0;
        while(left<=right) {
            mid = left+(right-left)/2;

            if((nums[mid]!=nums[mid-1]) && nums[mid]!=nums[mid+1])
                break;

            // the odd length part of the array has the element
            if((mid%2==0 && nums[mid]==nums[mid+1]) ||
                    (mid%2==1 && nums[mid]==nums[mid-1])) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return nums[mid];
    }
}
