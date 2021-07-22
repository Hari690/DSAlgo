package binarysearch;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which
 * appears exactly once. Find this single element that appears only once.
 *
 * Follow up: Your solution should run in O(log n) time and O(1) space.
 */
public class FirstNonRepeatedElement {
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
