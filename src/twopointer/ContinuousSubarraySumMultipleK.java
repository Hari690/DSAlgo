package twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up
 * to a multiple of k, or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 */
public class ContinuousSubarraySumMultipleK {

    /*
        Trick is if remainder after mod with a number exists, then it's divisible find index of prev
        occurrence of that and see size of array is greater than 2
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int sum=0;
        map.put(0,-1);
        for( int i=0;i<nums.length;i++) {
            sum+=nums[i];
            sum=sum%k;
            Integer prev = map.get(sum);
            // only if not found update map. If found we want to find max consecutive so we leave it.
            if(prev!=null) {
                if(i-prev>1)
                    return true;
            }
            else
                // maintain last index where sum was found.
                map.put(sum, i);

        }
        return false;
    }
}
