package deque;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 *
 * Input: nums = [1], k = 1
 * Output: 1
 * Example 2:
 *
 * Input: nums = [1,2], k = 4
 * Output: -1
 * Example 3:
 *
 * Input: nums = [2,-1,2], k = 3
 * Output: 3
 */
public class ShortestSubarraySumAtleastK {

    /*
        Calculate prefix sums first for all elements of the array with (length+1) starting with 0.
        We place index of elements as per prefixSum into a deque.
        We have 2 cases to pop elements hence Deque fits better here.
        We poll from first(beginning) whenever the sum in window is greater than equal k.
        We poll from last(end) whenever there's 0 or negative number so that queue has monotonically increasing values.
        This case is when we want to extend the array with more values.
     */
    public int shortestSubarray(int[] nums, int k) {
        int nLen = nums.length;
        int shortest = nLen + 1;

        // Building a prefix/cummulative sum of all elements
        int[] prefixSum = new int[nLen + 1];
        for(int i=0; i < nLen; i++)
            prefixSum[i+1] = prefixSum[i] + nums[i];

        // Initialize the Deque for storing the starting indices
        Deque<Integer> startIdxs = new ArrayDeque<>();

        for(int i=0; i < nLen + 1; i++){
            // Finding the smallest window whose sum >= k
            while(!startIdxs.isEmpty() && prefixSum[i] - prefixSum[startIdxs.peekFirst()] >= k)
                shortest = Math.min(shortest, i - startIdxs.pollFirst());

            // Keeping the startIdxs deque in an order of increasing sequence
            while(!startIdxs.isEmpty() && prefixSum[i] <= prefixSum[startIdxs.peekLast()])
                startIdxs.pollLast();

            // Add the current index to the startIdxs queue
            startIdxs.add(i);
        }

        return shortest <= nLen ? shortest : -1;
    }

    public static void main(String[] args) {
        ShortestSubarraySumAtleastK solution = new ShortestSubarraySumAtleastK();
//        int[] nums = {56,-21,56,35,-9};
//        solution.shortestSubarray(nums,61);
        int[] nums = {2,-1,2};
        solution.shortestSubarray(nums,3);
    }
}
