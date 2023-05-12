package dynamicprogramming;

import java.util.Arrays;

/**
 * You are given two integer arrays nums1 and nums2. We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.
 * We may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:
 *
 * nums1[i] == nums2[j], and
 * the line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only belong to one connecting line).
 *
 * Return the maximum number of connecting lines we can draw in this way.
 */
public class UncrossedLinesBridges {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] cache = new int[nums1.length][nums2.length];
        for(int i=0;i<nums1.length;i++)
            Arrays.fill(cache[i], -1);

        return helper(nums1, nums2, 0, 0, cache);
    }

    private int helper(int[] nums1,int[] nums2,int index1,int index2, int[][] cache) {
        if(index1==nums1.length || index2==nums2.length)
            return 0;

        if(cache[index1][index2]!=-1)
            return cache[index1][index2];

        if(nums1[index1]==nums2[index2]) {
            cache[index1][index2] = 1+helper(nums1,nums2,index1+1,index2+1, cache);
            return cache[index1][index2];
        }

        cache[index1][index2] = Math.max(helper(nums1,nums2,index1+1,index2, cache),
                helper(nums1,nums2,index1,index2+1, cache));
        return cache[index1][index2];
    }

    public static void main(String[] args) {
        UncrossedLinesBridges uncrossedLinesBridges = new UncrossedLinesBridges();
        int[] nums1 = {1,4,2};
        int[] nums2 = {1,2,4};
        uncrossedLinesBridges.maxUncrossedLines(nums1, nums2);
    }
}
