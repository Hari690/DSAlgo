package heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.
 * Return any such subsequence as an integer array of length k.
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: nums = [2,1,3,3], k = 2
 * Output: [3,3]
 * Explanation:
 * The subsequence has the largest sum of 3 + 3 = 6.
 * Example 2:
 * Input: nums = [-1,-2,3,4], k = 3
 * Output: [-1,3,4]
 * Explanation:
 * The subsequence has the largest sum of -1 + 3 + 4 = 6.
 * Example 3:
 *
 * Input: nums = [3,4,3,3], k = 2
 * Output: [3,4]
 * Explanation:
 * The subsequence has the largest sum of 3 + 4 = 7.
 * Another possible subsequence is [4, 3].
 */
public class FindSubsequenceWithLargestSum {
    class ValIndex {
        int val=0;
        int index=0;
        ValIndex(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
    class Solution {
        public int[] maxSubsequence(int[] nums, int k) {
            PriorityQueue<ValIndex> minHeap = new PriorityQueue<>((a, b)->a.val-b.val);
            for(int i=0;i<nums.length;i++){
                minHeap.add(new ValIndex(nums[i],i));
                if(minHeap.size()>k)
                    minHeap.poll();
            }
            List<ValIndex> result = new ArrayList<>();
            while(!minHeap.isEmpty()) {
                result.add(minHeap.poll());
            }
            Collections.sort(result,(a, b)->a.index-b.index);
            int[] arr = new int[result.size()];
            int index=0;
            for(ValIndex valIndex : result)
                arr[index++] = valIndex.val;
            return arr;
        }
    }
}
