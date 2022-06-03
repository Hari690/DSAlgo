package problems;

import java.util.Arrays;

/*
    Think bottom up but start top down and use memoization.
 */
public class UniquePathsAndObstacles {

    public static void main(String[] args) {
        int[][] matrix1 = {{0,0},{0,1}};
        new UniquePathsAndObstacles().uniquePathsWithObstaclesDFS(matrix1);

        int[][] matrix2 = {{1,3,1},{1,5,1},{4,2,1}};
        new UniquePathsAndObstacles().minPathSum(matrix2);
    }

    public int uniquePaths(int m, int n) {
        // if(m==1 || n==1) {
        //     return 1;
        // }
        // return uniquePaths(m-1, n) + uniquePaths(m, n-1);

        int[][] count = new int[m][n];

        for(int i=0;i<m;i++) {
            count[i][0] = 1;
        }

        for(int i=0;i<n;i++) {
            count[0][i] = 1;
        }

        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++) {
                count[i][j] = count[i-1][j] + count[i][j-1];
            }
        }
        return count[m-1][n-1];
    }

    /**
     * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
     * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
     * The test cases are generated so that the answer will be less than or equal to 2 * 109.
     * Input: m = 3, n = 7
     * Output: 28
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsDFS(int m, int n) {
        int[][] grid = new int[m][n];
        int[][] visited = new int[m][n];
        for(int i=0;i<visited.length;i++)
            Arrays.fill(visited[i],-1);
        return dfs(grid, 0, 0, visited);
    }

    /**
     * You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m-1][n-1]). The robot can only move either down or right at any point in time.
     * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
     * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
     * The testcases are generated so that the answer will be less than or equal to 2 * 109.
     * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * Output: 2
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstaclesDFS(int[][] obstacleGrid) {
        int[][] visited = new int[obstacleGrid.length][obstacleGrid[0].length];
        for(int i=0;i<visited.length;i++)
            Arrays.fill(visited[i],-1);
        return dfs(obstacleGrid, 0, 0, visited);
    }

    int dfs(int[][] obstacleGrid, int i, int j, int[][] visited) {
        if(i<0 || j<0 || i==obstacleGrid.length || j==obstacleGrid[0].length || obstacleGrid[i][j]==1)
            return 0;

        if(visited[i][j]!=-1)
            return visited[i][j];

        if(i==obstacleGrid.length-1 && j==obstacleGrid[0].length-1) {
            return 1;
        }

        int noWays = 0;
        noWays+=dfs(obstacleGrid, i+1, j, visited);
        noWays+=dfs(obstacleGrid, i, j+1, visited);
        visited[i][j]=noWays;
        return noWays;
    }


    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] sum = new int[m][n];

        sum[0][0] = grid[0][0];
        for(int i=1;i<m;i++) {
            sum[i][0] = sum[i-1][0]+grid[i][0];
        }

        for(int i=1;i<n;i++) {
            sum[0][i] = sum[0][i-1]+grid[0][i];
        }

        for(int i=1;i<m;i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = grid[i][j]+Math.min(sum[i-1][j],sum[i][j-1]);
            }
        }

        return sum[m-1][n-1];
    }

    /**
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
     * Note: You can only move either down or right at any point in time.
     *
     * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
     * Output: 7
     * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
     */
    public int minPathSumDFS(int[][] grid) {
        if(grid.length==1 && grid[0].length==1)
            return grid[0][0];
        int[][] minSums = new int[grid.length][grid[0].length];
        for(int i=0;i<minSums.length;i++)
            Arrays.fill(minSums[i],-1);
        minPathSumDFS(grid, 0, 0, minSums);
        return minSums[0][0];
    }

    int minPathSumDFS(int[][] grid, int i, int j, int[][] minSums) {
        if(i==grid.length || j==grid[0].length)
            return Integer.MAX_VALUE;

        if(i==grid.length-1 && j==grid[0].length-1)
            return grid[i][j];

        if(minSums[i][j]!=-1)
            return minSums[i][j];

        int right = minPathSumDFS(grid, i+1, j, minSums);
        int down = minPathSumDFS(grid, i, j+1, minSums);
        minSums[i][j]=Math.min(down, right) + grid[i][j];
        return minSums[i][j];
    }
}
