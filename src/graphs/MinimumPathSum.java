package graphs;

import java.util.Arrays;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++)
            Arrays.fill(dp[i], -1);
        return dfs(0, 0, grid, dp);
    }

    private int dfs(int i, int j,int[][] grid, int[][] dp) {
        if(i<0 || i==grid.length || j<0 || j==grid[0].length)
            return Integer.MAX_VALUE;

        if(i==grid.length-1 && j==grid[0].length-1)
            return grid[i][j];

        if(dp[i][j]!=-1)
            return dp[i][j];

        dp[i][j] = grid[i][j]+Math.min(dfs(i+1,j,grid,dp),dfs(i,j+1,grid,dp));
        return dp[i][j];
    }
}
