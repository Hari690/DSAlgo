package twopointer;

/**
 * An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
 * Given an integer array nums, return the number of arithmetic subarrays of nums.
 * A subarray is a contiguous subsequence of the array.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: 3
 * Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 0
 */
public class ArithmeticSlices {

    /*
    sum += curr really does the trick. Brilliant!

    I think the easy way to understand this is that adding current number to our existing arithmetic sequence,
    we will have curr additional combinations of new arithmetic slices.

    Let's say if we have [1, 2, 3, 4] and currently we have 3 arithmetic slices (curr is 2). We are going to
    add 5 to our arithmetic sequence. So that we will have curr new slices (curr is 3), which is [3, 4, 5], [2, 3, 4, 5]
    and [1, 2, 3, 4, 5]. Now, the total valid arithmetic slices is 3 + curr = 6. That's exactly the same as sum += curr.
     */
    public int numberOfArithmeticSlices(int[] A) {
        int curr = 0, sum = 0;
        for (int i=2; i<A.length; i++)
            if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
                curr += 1;
                sum += curr;
            } else {
                curr = 0;
            }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        ArithmeticSlices arithmeticSlices = new ArithmeticSlices();

        arithmeticSlices.numberOfArithmeticSlices(arr);
    }
}
