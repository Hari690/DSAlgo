package array;

import java.util.Arrays;

/**
 * Given an array of integers nums, a move consists of choosing any nums[i], and incrementing it by 1.
 * Return the least number of moves to make every value in nums unique.
 */
public class MinIncrementForUnique {
    public int minIncrementForUnique(int[] A) {
        // Sort the input array.
        // Compared with previous number,
        // the current number need to be at least prev + 1.

        // Time Complexity: O(NlogN) for sorting
        // Space: O(1) for in-space sort
        if(A == null || A.length == 0){
            return 0;
        }
        Arrays.sort(A);
        int res = 0, prev = A[0];
        for(int i = 1; i < A.length; i++){
            int expect = prev + 1;
            res += A[i] > expect ? 0 : expect - A[i];
            prev = Math.max(expect, A[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        MinIncrementForUnique minIncrementForUnique = new MinIncrementForUnique();

        int[] a = {3,2,1,2,1,7};
        minIncrementForUnique.minIncrementForUnique(a);
    }
}
