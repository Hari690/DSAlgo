package problems;

import java.util.Arrays;

public class FirstAndLastOccurenceSortedArray {

    public static void main(String[] args) {

        int[] nums = {1,2,3,6,6,6,6,8,9};
        Arrays.stream(new FirstAndLastOccurenceSortedArray().searchInsert(nums, 6)).forEach(s->System.out.println(s));

    }

    public int[] searchInsert(int[] nums, int target) {
        int startIndex = getStartingIndex(nums, target);
        int endIndex = getEndingIndex(nums, target);
        int[] result = {startIndex,endIndex};
        return result;
    }

    public int getStartingIndex(int[] nums, int target) {

        int index = -1;
        int start = 0;
        int end = nums.length - 1;

        while(start<=end) {
            int midpoint = start + (end - start)/2;
            if(nums[midpoint]>=target) {
                end = midpoint-1;
            } else if(nums[midpoint]<target) {
                start = midpoint+1;
            }
            if(nums[midpoint]==target) {
                index = midpoint;
            }
        }
        return index;

    }

    public int getEndingIndex(int[] nums, int target) {

        int index = -1;
        int start = 0;
        int end = nums.length - 1;

        while(start<=end) {
            int midpoint = start + (end - start)/2;
            if(nums[midpoint]<=target) {
                start = midpoint+1;
            } else if(nums[midpoint]>target) {
                end = midpoint-1;
            }
            if(nums[midpoint]==target) {
                index = midpoint;
            }
        }
        return index;
    }
}
