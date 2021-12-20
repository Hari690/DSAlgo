package twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
 * A subarray is a contiguous part of the array.
 */
public class NumSubarraysWithSum {
    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer,Integer> presum = new HashMap<>();

        int right=0, sum=0, total=0;

        // base case
        presum.put(0, 1);
        while(right<nums.length) {
            sum+=nums[right++];
            if(presum.get(sum-goal)!=null)
                total+=presum.get(sum-goal);
            presum.put(sum, presum.getOrDefault(sum,0)+1);
        }
        return total;
    }
}
