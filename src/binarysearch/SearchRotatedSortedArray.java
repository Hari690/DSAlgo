package binarysearch;

public class SearchRotatedSortedArray {
    //[4,5,6,7,0,1,2]
    //[7,0,1,2,3,4,5]
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        while( left<right) {
            int mid = left+(right-left)/2;
            if (nums[mid]>nums[right]) {
                left = mid+1;
            } else {
                right = mid;
            }
        }

        int start = left;
        left = 0;
        right = nums.length - 1;
        if(target>=nums[start] && target<=nums[right]) {
            left = start;
        } else {
            right = start;
        }

        int index = -1;
        while( left<=right) {
            int mid = left+(right-left)/2;
            if( nums[mid] == target) {
                index = mid;
                break;
            }
            if (nums[mid]<target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return index;
    }
}
