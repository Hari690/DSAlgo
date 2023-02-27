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

    // Keep track of max position we can reach and set that to a variable - curEnd for each jump.
    // When we reach same index as that variable(max position where jump completed) then increment jumps and set the farthest to currMax.
    // jumps will track the no of jumps we make while curEnd will keep track of where we made the best jump.
    // This is an implicit bfs solution. i == curEnd means you visited all the items on the current level.
    // Incrementing jumps++ is like incrementing the level you are on.
    // And curEnd = curFarthest is like getting the queue size (level size) for the next level you are traversing.
    public int jumpO1(int[] A) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + A[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }

            if (curEnd >= A.length - 1) {
                break;
            }
        }
        return jumps;
    }

    public int jumpBFS(int[] nums) {
        int l=0,r=0,max=0,steps=0;
        // we need to go only until r reaches the last index.
        while(r<nums.length-1) {
            for(int i=l;i<=r;i++) {
                max = Math.max(max,nums[i]+i);
            }
            l=r+1;
            r=max;
            steps++;
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] arr = {2,1,1,1,1};
        JumpGame2 jumpGame = new JumpGame2();
        jumpGame.jumpBFS(arr);
    }
}
