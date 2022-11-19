package dynamicprogramming;

import java.util.Arrays;

/**
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
 * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 *
 * Example 2:
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 *
 * Example 3:
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 */
public class MinSumOfMaxSubArrays {
    public int minDifficulty(int[] jobDifficulty, int d) {

        if(jobDifficulty.length<d)
            return -1;

        int[][] dp = new int[d+1][jobDifficulty.length];

        for(int i=0;i<d+1;i++)
            Arrays.fill(dp[i], -1);

        return dfs(jobDifficulty,0, d, dp);
    }

    private int dfs(int[] jobDifficulty, int index, int d, int[][] dp) {
        if(d==1) {
            int max = 0;
            for(int j=index;j<jobDifficulty.length;j++) {
                max = Math.max(jobDifficulty[j], max);
            }
            return max;
        }

        if(dp[d][index]!=-1)
            return dp[d][index];

        int leftMax=0;
        int res = Integer.MAX_VALUE;
        // check [1,1,1] , 3 so we leave enough days for the other tasks.
        for(int i=index;i<jobDifficulty.length-d+1;i++) {
            leftMax = Math.max(leftMax, jobDifficulty[i]);
            res=Math.min(res, leftMax+dfs(jobDifficulty,i+1,d-1,dp));
        }
        dp[d][index]=res;
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1};
        MinSumOfMaxSubArrays minSumOfMaxSubArrays = new MinSumOfMaxSubArrays();
        minSumOfMaxSubArrays.minDifficulty(arr,3);
    }
}
