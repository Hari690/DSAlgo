package dynamicprogramming;

import java.util.Arrays;

/**
 * Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
 * The answer is guaranteed to fit in a 32-bit integer.
 * Input: nums = [1,2,3], target = 4
 * Output: 7
 * Explanation:
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 */
public class CombinationSum4 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target];

        Arrays.fill(dp, -1);

        return calculate(nums, target, 0, dp);
    }

    private int calculate(int[] nums, int target, int sum, int[] dp) {
        if(sum==target){
            return 1;
        }
        if(sum>target) {
            return 0;
        }
        if(dp[sum]!=-1)
            return dp[sum];

        int total=0;
        for(int i=0;i<nums.length;i++) {
            total+=calculate(nums, target, sum+nums[i], dp);
            dp[sum] = total;
        }
        return total;
    }
}
