package array;

import java.util.HashSet;
import java.util.Set;

/**
 * An n x n matrix is valid if every row and every column contains all the integers from 1 to n (inclusive).
 * Given an n x n integer matrix matrix, return true if the matrix is valid. Otherwise, return false.
 * Input: matrix = [[1,2,3],[3,1,2],[2,3,1]]
 * Output: true
 * Explanation: In this case, n = 3, and every row and column contains the numbers 1, 2, and 3.
 * Hence, we return true.
 *
 */
public class CheckMatrixHasN {
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        for(int r=0;r<n;r++) {
            Set<Integer> row = new HashSet<>();
            Set<Integer> col = new HashSet<>();
            for (int c = 0; c < n; ++c) {
                // we need to check only for repetitions by going in column and row direction.
                if (!row.add(matrix[r][c]) || !col.add(matrix[c][r])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CheckMatrixHasN checkMatrixHasN = new CheckMatrixHasN();
        int[][] matrix = {{1,2,3},{1,2,3},{1,2,3}};
        checkMatrixHasN.checkValid(matrix);
    }
}
