package dynamicprogramming;

/**
 * Given an m x n binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 */
public class MaxSquare {
    public int maximalSquare(char[][] matrix) {
        int[][] noMatrix = new int[matrix.length+1][matrix[0].length+1];
        int max = 0;
        for(int i=1;i<=matrix.length;i++) {
            for(int j=1;j<=matrix[0].length;j++) {
                if(matrix[i-1][j-1]=='1') {
                    noMatrix[i][j] = Math.min(Math.min(noMatrix[i-1][j],noMatrix[i][j-1]),noMatrix[i-1][j-1])+1;
                    max = Math.max(noMatrix[i][j],max);
                }
            }
        }
        return max*max;
    }
}
