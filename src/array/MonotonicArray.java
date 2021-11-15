package array;

/**
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 * An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j]. An array nums is monotone decreasing if for all i <= j,
 * nums[i] >= nums[j].
 * Given an integer array nums, return true if the given array is monotonic, or false otherwise.
 */
public class MonotonicArray {

    // use two boolean and check at the end.
    public boolean isMonotonic(int[] A) {
        boolean inc = true, dec = true;
        for (int i = 1; i < A.length; ++i) {
            inc &= A[i - 1] <= A[i];
            dec &= A[i - 1] >= A[i];
            if (!inc && !dec) return false;
        }
        return inc || dec;
    }
}
