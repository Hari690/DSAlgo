package graphs;

import java.util.Arrays;

/**
 * There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn].
 * You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary).
 * You can apply at most maxMove moves to the ball.
 *
 * Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary.
 * Since the answer can be very large, return it modulo 109 + 7.
 *
 * Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * Output: 6
 *
 * Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * Output: 12
 */
public class FindPathsGetOut {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[m][n][maxMove+1];
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                Arrays.fill(dp[i][j],-1);

        return dfs(m,n,startRow,startColumn, 0, maxMove, dp);
    }

    private int dfs(int m, int n, int i, int j, int moves, int maxMoves,int[][][] dp) {
        if(moves>maxMoves)
            return 0;

        if(i==m || i==-1 || j==n || j==-1) {
            return 1;
        }

        if(dp[i][j][moves]!=-1) {
            return dp[i][j][moves];
        }

        long total = 0;
        total+=dfs(m, n, i+1, j, moves+1, maxMoves, dp);
        total+=dfs(m, n, i, j+1, moves+1, maxMoves, dp);
        total+=dfs(m, n, i-1, j, moves+1, maxMoves, dp);
        total+=dfs(m, n, i, j-1, moves+1, maxMoves, dp);
        dp[i][j][moves] = (int)(total%1000000007);
        return dp[i][j][moves];
    }

    public static void main(String[] args) {
        FindPathsGetOut findPathGetOut = new FindPathsGetOut();

        System.out.println(findPathGetOut.findPaths(2,2,2,0,0));
    }
}
