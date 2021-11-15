package binarysearch;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 */
public class PerfectSquare {
    public boolean isPerfectSquare(int num) {
        if (num == 0 || num == 1)
            return true;
        int low = 1, high = num/2;
        while (low <= high) {
            long mid = low + (high-low)/2;
            if (mid == num/mid) {
                return true;
            } else if (mid < num/mid) {
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        return false;
    }
}
