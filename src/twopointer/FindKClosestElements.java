package twopointer;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be
 * sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 * This is done using linear search but the correct solution is Binary search.
 */
public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - 1;
        while (hi - lo >= k) {
            if (Math.abs(arr[lo] - x) > Math.abs(arr[hi] - x)) {
                lo++;
            } else {
                hi--;
            }
        }
        List<Integer> result = new ArrayList<>(k);
        for (int i = lo; i <= hi; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}
