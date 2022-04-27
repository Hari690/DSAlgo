package array;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 *
 * 3231  // find 2 where peak exists.
 * 3321  // swap with element greater than peak to right.
 * 3312  // swap remaining elements or reverse it.
 */
public class NextPermutation {
    /*
        1. Peak rightmost element.
        2. Find element to right of peak greater than peak.
        3. Swap that element and element to left of peak.
        4. Reverse or sort elements to right of that element to get next greatest.
        The idea is to find the first element from the right where it breaks ascending order.
        Then check against the element on the left where the next greater element is on the right side.
        Swap that local maxima with the element on the left.
        Sort or reverse using swap the remaining part of the array.
     */
    public void nextPermutation(int[] A) {
        if(A == null || A.length <= 1) return;
        int i = A.length - 2;
        while(i >= 0 && A[i] >= A[i + 1]) i--; // Find 1st id i that breaks descending order
        if(i >= 0) {                           // If not entirely descending
            int j = A.length - 1;              // Start from the end
            while(A[j] <= A[i]) j--;           // Find rightmost first larger id j
            swap(A, i, j);                     // Switch i and j
        }
        reverse(A, i + 1, A.length - 1);       // Reverse the descending sequence
        //Arrays.sort(A, i + 1, A.length);
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public void reverse(int[] A, int i, int j) {
        while(i < j) swap(A, i++, j--);
    }
}
