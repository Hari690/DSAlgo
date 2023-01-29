package backtracking;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 * Example 2:
 * Input: nums = [5], k = 9
 * Output: 0
 */
public class SubarraysSumDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer,Integer> sumMap = new HashMap<>();
        sumMap.put(0, 1);
        int sum=0;
        int total=0;
        for(int i=0;i<nums.length;i++) {
            sum=(sum+nums[i])%k;
            // Because -1 % 5 = -1, but we need the positive mod
            if(sum<0)
                sum+=k;
            if(sumMap.containsKey(sum))
                total+=sumMap.get(sum);
            sumMap.put(sum, sumMap.getOrDefault(sum,0)+1);
        }
        return total;
    }

    public static void main(String[] args) {
        SubarraysSumDivisibleByK subarraysSumDivisibleByK = new SubarraysSumDivisibleByK();
        int[] nums = {2,-2,2,-4};
        subarraysSumDivisibleByK.subarraysDivByK(nums, 6);
    }
}
