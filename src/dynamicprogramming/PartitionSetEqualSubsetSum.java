package dynamicprogramming;

import java.util.Map;

/**
 * Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets such that the
 * sum of elements in both subsets is equal.
 *
 * Example 1:
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
/*
    This is the Subset sum problem.
    Similar technique can be used to also solve Minimum subset sum difference where we can try to do subset sum against sum/2 and try
    to find the value closest to it which can represent sum of a single set.
    It's a variation of 0/1 knapsack.
 */
public class PartitionSetEqualSubsetSum {

    public static void main(String[] args) {
        int[] nums = {2,2,1,5};
        new PartitionSetEqualSubsetSum().canPartition(nums);
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

    private boolean check(int[] nums, int sum, int index, int total, Map<String,Boolean> map) {
        if(index==nums.length || total>sum)
            return false;

        if(total==sum)
            return true;

        String key = index + " " + total;
        if(map.containsKey(key))
            return map.get(key);

        boolean result = check(nums,sum,index+1,total+nums[index],map) ||
            check(nums,sum,index+1,total,map);
        map.put(key, result);
        return result;
    }
}
