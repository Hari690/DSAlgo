package dynamicprogramming;

/**
 * You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.
 * Return the number of strictly increasing paths in the grid such that you can start from any cell and end at any cell. Since the answer may be very large, return it modulo 109 + 7.
 * Two paths are considered different if they do not have exactly the same sequence of visited cells.
 *
 * Example 1:
 * Input: grid = [[1,1],[3,4]]
 * Output: 8
 * Explanation: The strictly increasing paths are:
 * - Paths with length 1: [1], [1], [3], [4].
 * - Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
 * - Paths with length 3: [1 -> 3 -> 4].
 * The total number of paths is 4 + 3 + 1 = 8.
 *
 * Example 2:
 *
 * Input: grid = [[1],[2]]
 * Output: 3
 * Explanation: The strictly increasing paths are:
 * - Paths with length 1: [1], [2].
 * - Paths with length 2: [1 -> 2].
 * The total number of paths is 2 + 1 = 3.
 */
public class NumberIncreasingPathsInGridMatrix {
    int MOD = 1000000007;
    public int countPaths(int[][] matrix) {
        long total = 0;
        long[][] cache = new long[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                total=(total+dfs(matrix, i, j, -1, cache))%MOD;
            }
        }

        return (int)total;
    }

    private long dfs(int[][] matrix, int row, int col, int prev, long[][] cache) {
        if(row>=matrix.length || col>=matrix[0].length || row<0 ||
                col<0 || matrix[row][col]<=prev)
            return 0;

        if(cache[row][col]!=0)
            return cache[row][col];

        long total = 1l;
        total=(total+dfs(matrix, row+1, col, matrix[row][col], cache))%MOD;
        total=(total+dfs(matrix, row, col+1, matrix[row][col], cache))%MOD;
        total=(total+dfs(matrix, row-1, col, matrix[row][col], cache))%MOD;
        total=(total+dfs(matrix, row, col-1, matrix[row][col], cache))%MOD;
        cache[row][col]=total;
        return cache[row][col];
    }
}
