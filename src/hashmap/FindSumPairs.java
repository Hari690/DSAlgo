package hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * You are given two integer arrays nums1 and nums2. You are tasked to implement a data structure that supports queries of two types:
 *
 * Add a positive integer to an element of a given index in the array nums2.
 * Count the number of pairs (i, j) such that nums1[i] + nums2[j] equals a given value (0 <= i < nums1.length and 0 <= j < nums2.length).
 * Implement the FindSumPairs class:
 *
 * FindSumPairs(int[] nums1, int[] nums2) Initializes the FindSumPairs object with two integer arrays nums1 and nums2.
 * void add(int index, int val) Adds val to nums2[index], i.e., apply nums2[index] += val.
 * int count(int tot) Returns the number of pairs (i, j) such that nums1[i] + nums2[j] == tot.
 */
public class FindSumPairs {
    /*
        Since num1 is small array , we can store the array itself for iteration.
        Map2 has value -> count which can be updated by storing num2 array also.
     */
    private Map<Integer, Integer> map = new HashMap<>();
    private int[] nums1, nums2;
    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        for(int num: nums2)
            map.put(num, map.getOrDefault(num, 0) + 1);
    }

    public void add(int index, int val) {
        map.put(nums2[index], map.get(nums2[index]) - 1);
        nums2[index] += val;
        map.put(nums2[index], map.getOrDefault(nums2[index], 0) + 1);
    }

    public int count(int tot) {
        int result =0;
        for(int num: nums1)
            if(map.containsKey(tot - num))
                result += map.get(tot - num);
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2, 2, 2, 3};
        int[] arr2 = {1, 4, 5, 2, 5, 4};
        FindSumPairs findSumPairs = new FindSumPairs(arr1,arr2);
        findSumPairs.count(7);
    }
}
