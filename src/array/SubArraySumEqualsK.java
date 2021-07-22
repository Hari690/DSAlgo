package array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 */
public class SubArraySumEqualsK {

    /**
     * Cannot use sliding window because of negative numbers as we expand window case becomes cane be valid which we rejected before.
     * We can find a specific case such that it doesn't hold, e.g., if K is 2, 1,1,1 sum is 3, so 1,1,1, is invalid,
     * but 1,1,1,-1 sum is 2 which means 1,1,1,-1 is valid, so rule breaks.
     * Instead we use a HashMap and see if the sum-K was already seen previously and add those values to a count.
     * Eg : if we have few subarrays with sum 6 from beginning as below so sum - k  = 3, there are 4 available which can be found from
     * the HashMap. HashMap always holds total sum from beginning.
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            if ( sum ==k)
                count++;
            // no of time of occurence
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,-1,1,1,-1};
        new SubArraySumEqualsK().subarraySum(nums, 6);
    }
}
