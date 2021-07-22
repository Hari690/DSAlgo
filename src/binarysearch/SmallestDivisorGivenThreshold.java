package binarysearch;

/**
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and
 * sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
 *
 * Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
 *
 * It is guaranteed that there will be an answer.
 */
public class SmallestDivisorGivenThreshold {
    public int smallestDivisor(int[] nums, int threshold) {
        int n = nums.length, l = 1, r = getMax(nums);
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (getDiv(nums, mid) <= threshold) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    private int getMax(int[] nums) {
        int res = 1;
        for (int n : nums) res = Math.max(res, n);
        return res;
    }

    private long getDiv(int[] nums, int d) {
        long res = 0;
        for (int n : nums) {
            res += n / d + (n % d == 0 ? 0 : 1);
        }
        return res;
    }
}
