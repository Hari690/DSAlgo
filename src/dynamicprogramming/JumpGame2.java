package dynamicprogramming;

import java.util.Arrays;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 */
public class JumpGame2 {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, nums.length+1);
        dp[0]=0;

        for(int i=0;i<nums.length;i++) {
            for(int j=i+1;j<=i+nums[i] && j<nums.length;j++) {
                dp[j] = Math.min(dp[j],dp[i]+1);
            }
        }

        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        int[] arr = {2,3,1,1,4};
        JumpGame2 jumpGame = new JumpGame2();
        jumpGame.jump(arr);
    }
}
