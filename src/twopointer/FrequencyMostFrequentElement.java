package twopointer;

import java.util.Arrays;

/**
 * The frequency of an element is the number of times it occurs in an array.
 *
 * You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at
 * that index by 1.
 *
 * Return the maximum possible frequency of an element after performing at most k operations.
 */
public class FrequencyMostFrequentElement {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int max=0;
        long sum=0;
        for(int i=0;i<nums.length;i++) {
            sum+=nums[i];

            while(nums[i]*(i-left+1)*1l>(sum+k)) {
                sum-=nums[left];
                left++;
            }

            max = Math.max((i-left+1), max);
        }
        return max;
    }
}
