package twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element
 * from the array nums and subtract its value from x. Note that this modifies the array for future operations.
 *
 * Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.
 */
public class MinOperationXZero {
    // boils down to max length of mid subarray with sum-target as it's sum.
    public int minOperations(int[] nums, int target) {
        int sum = 0;
        for( int num : nums) {
            sum+=num;
        }
        if(target>sum) {
            return -1;
        }
        if(sum==target) {
            return nums.length;
        }
        if(sum<0)
            return -1;
        sum-=target;
        Map<Integer, Integer> map = new HashMap<>();
        int runningSum = 0;
        int minLength = 0;
        map.put(0,-1);
        for(int i=0;i<nums.length;i++) {
            runningSum+=nums[i];
            if(map.containsKey(runningSum-sum)) {
                minLength = Math.max(minLength, i-map.get(runningSum-sum));
            }
            map.put(runningSum, i);
        }
        return (minLength==0)?-1:nums.length-minLength;
    }

    public int minOperationsSW(int[] nums, int x) {
        int sum = 0;

        for(int num : nums)
            sum+=num;

        sum = sum - x;
        if(sum==0)
            return nums.length;

        int currSum = 0;
        int left = 0;
        int max = 0;
        for(int i=0;i<nums.length;i++) {
            currSum+=nums[i];
            while(left<=i && currSum>sum) {
                currSum-=nums[left];
                left++;
            }
            if(currSum==sum)
                max = Math.max(max, i-left+1);
        }
        return (max==0)?-1:(nums.length - max);
    }
}
