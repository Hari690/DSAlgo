package graphs;

/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the
 * boundary (i.e., wrap-around is not allowed).
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 */
public class LongestIncreasingPathInMatrix {
    int max = 0;
    public int longestIncreasingPath(int[][] matrix) {
        int[][] cache = new int[matrix.length][matrix[0].length];

        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                dfs(matrix, i, j, -1, cache);
            }
        }

        return max;
    }

    private int dfs(int[][] matrix, int row, int col, int prev, int[][] cache) {
        if(row>=matrix.length || col>=matrix[0].length || row<0 ||
            col<0 || matrix[row][col]<=prev)
            return 0;

        if(cache[row][col]!=0)
            return cache[row][col];

        int max1,max2,max3,max4 = 0;
        max1 = 1 + dfs(matrix, row+1, col, matrix[row][col], cache);
        max2 = 1 + dfs(matrix, row, col+1, matrix[row][col], cache);
        max3 = 1 + dfs(matrix, row-1, col, matrix[row][col], cache);
        max4 = 1 + dfs(matrix, row, col-1, matrix[row][col], cache);
        cache[row][col]=Math.max(Math.max(Math.max(max1,max2),max3),max4);
        max = Math.max(max, cache[row][col]);
        return cache[row][col];
    }
}
