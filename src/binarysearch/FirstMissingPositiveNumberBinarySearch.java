package binarysearch;

/**
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 *
 * Example 1:
 * Input: nums = [1,2,0]
 *
 * Output: 3
 * Explanation: The numbers in the range [1,2] are all in the array.
 * Example 2:
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Explanation: 1 is in the array but 2 is missing.
 * Example 3:
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * Explanation: The smallest positive integer 1 is missing.
 */
public class FirstMissingPositiveNumberBinarySearch {

    /*
        One option is to search between 1 and array.length+1 and check against a set which contains the nos. alternative is by marking the array.
     */
    public int findFirstMissing(int array[], int start, int end)
    {
        if (start > end)
            return end + 1;

        if (start != array[start])
            return start;

        int mid = (start + end) / 2;

        // Left half has all elements from 0 to mid
        if (array[mid] == mid)
            return findFirstMissing(array, mid+1, end);

        return findFirstMissing(array, start, mid);
    }

    public int findFirstMissing(int[] array)
    {
        int start = 0;
        int end = array.length - 1;

        while(start<=end) {
            int mid = start + (end-start)/2;
            if(mid==array[mid]) {
                start = mid + 1;
            } else {
                end = mid-1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        FirstMissingPositiveNumberBinarySearch small = new FirstMissingPositiveNumberBinarySearch();
        //int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 10};
        //int[] arr = {0, 1, 2, 8, 9, 10, 11, 12, 13};
        //int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int n = arr.length;
//        System.out.println("First Missing element is : "
//                + small.findFirstMissing(arr, 0, n - 1));
        System.out.println("First Missing element is : "
                + small.findFirstMissing(arr));


    }

    /*
        Numbers greater then n can be ignored because the missing integer must be in the range 1..n+1
        If each cell in the array were to contain positive integers only, we can use the negative of the stored number as a flag to mark something (in this case the flag indicates this index was found in some cell of the array)
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // 1. mark numbers (num < 0) and (num > n) with a special marker number (n+1)
        // (we can ignore those because if all number are > n then we'll simply return 1)
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        // note: all number in the array are now positive, and on the range 1..n+1

        // 2. mark each cell appearing in the array, by converting the index for that number to negative
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num > n) {
                continue;
            }
            num--; // -1 for zero index based array (so the number 1 will be at pos 0)
            if (nums[num] > 0) { // prevents double negative operations
                nums[num] = -1 * nums[num];
            }
        }

        // 3. find the first cell which isn't negative (doesn't appear in the array)
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                return i + 1;
            }
        }

        // 4. no positive numbers were found, which means the array contains all numbers 1..n
        return n + 1;
    }
}
