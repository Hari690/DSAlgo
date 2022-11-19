package twopointer;

import java.util.Arrays;

/**
 * You are given an array of integers nums and an integer target.
 *
 * Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target. Since the answer may be too large, return it modulo 109 + 7.
 *
 * Example 1:
 * Input: nums = [3,5,6,7], target = 9
 * Output: 4
 * Explanation: There are 4 subsequences that satisfy the condition.
 * [3] -> Min value + max value <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 *
 * Example 2:
 * Input: nums = [3,3,6,8], target = 10
 * Output: 6
 * Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 *
 * Example 3:
 * Input: nums = [2,3,3,4,6,7], target = 12
 * Output: 61
 * Explanation: There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
 * Number of valid subsequences (63 - 2 = 61).
 */
public class NumberOfSubsequenceSatisfyGivenSumCondition {
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);    //Sorting so that we can get the min and max directly
        int i=0,j=nums.length-1,count=0,mod=(int)1e9 + 7;
        int[] power=new int[nums.length];  //Calculating the power in the power array.
        power[0]=1; // no. of subsequences formed using one no. is 1
        for(int idx=1;idx<nums.length;idx++)
            power[idx]=(power[idx-1]*2)%mod;
        //Now if i is at 0 and j is at 3 then the no. of
        //subsequences keeping the first no. that is at i fixed we can form 8 subsequences, similarly for different values of i and j the no. of subsequences varies.
        // if this is the array
        // 3 5 6 7
        // i     j                      j-i=3.
        // then no. of subsequences formed keeping 3 constant is 8 which is stored in the power array (power[j-i]=8) just do a dry run of the formula. you will get 8 for gap of 3
        //3
        //3 5 6 7
        //3 5 6
        //3 5 7
        //3 6 7
        //3 5
        //3 6
        //3 7
        while(i<=j){
            if(nums[i]+nums[j]<=target){
                // count+=Math.pow(2,j-i);
                count=(count+power[j-i])%mod;
                i++;
            }else if(nums[i]+nums[j]>target)
                j--;
        }
        return count;
    }
}
