package dynamicprogramming;

import java.util.Arrays;

/**
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are
 * asked to burst all the balloons.
 *
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the
 * array, then treat it as if there is a balloon with a 1 painted on it.
 *
 * Return the maximum coins you can collect by bursting the balloons wisely.
 */

/*
Idea is to add a 1 to start and end and treat that array between boundary  start+1 and end-1.
Then for each step consider that element as last one to be burst and use left-1 and right+1
boundaries to calculate the value obtained because of it and add with value obtained from left
part and right part.
 */
public class BurstBaloons {
    private int burst(int[] nums, int left, int right, int[][] dp) {
        if(left>right)
            return 0;

        if(dp[left][right]!=-1)
            return dp[left][right];

        int ans = 0;
        for(int i=left;i<=right;i++) {
            ans = Math.max(ans,nums[i]*nums[left-1]*nums[right+1]+burst(nums, left,i-1, dp)+burst(nums,i+1,right, dp));
            dp[left][right]=ans;
        }
        return ans;
    }

    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;


        int[][] dp = new int[n][n];

        for(int i=0;i<dp.length;i++)
            Arrays.fill(dp[i],-1);

        return burst(nums, 1, n - 2, dp);
    }
}
