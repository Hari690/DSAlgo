package stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in
 * nums2. If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 */
public class NextGreatestElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Deque<Integer> stack = new LinkedList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums2) {
            while(stack.size()>0 && num>stack.peek())
                map.put(stack.pop(),num);

            stack.push(num);
        }

        int[] output = new int[nums1.length];
        int i=0;
        for(int num : nums1) {
            output[i++] = map.getOrDefault(num, -1);
        }
        return output;
    }
}
