package dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.OptionalInt;

/**
 * Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets such that the
 * sum of elements in both subsets is equal.
 */
/*
    This is the Subset sum problem.
    Similar technique can be used to also solve Minimum subset sum difference where we can try to do subset sum against sum/2 and try
    to find the value closest to it which can represent sum of a single set.
 */
public class PartitionSet {

    public static void main(String[] args) {
        int[] nums = {2,2,1,5};
        new PartitionSet().canPartition(nums);
    }

    public boolean canPartition(int[] nums) {

        int sum = 0;
        for( int num : nums) {
            sum+=num;
        }

        if(sum%2!=0)
            return false;

        sum = sum/2;
        boolean[][] dp = new boolean[nums.length][sum+1];

        for( int i=0;i<nums.length;i++) {
            dp[i][0] = true;
        }

        for(int i=0;i<nums.length;i++) {
            for( int j=1;j<=sum;j++) {
                if(i==0) {
                    if(j==nums[i])
                        dp[i][j] = true;
                } else {
                    if(j<nums[i])
                        dp[i][j] = dp[i-1][j];
                    else
                        dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
        }

        return dp[nums.length-1][sum];
    }
}
