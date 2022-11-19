package array;

/**
 * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.
 * Example 1:
 * Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * Output: true
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 * Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * Output: true
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 */
public class MatrixDiagonalEqual {
    public boolean isToeplitzMatrix(int[][] matrix) {
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                if(i==0 || j==0) {
                    if(!checkDiagonal(i,j,matrix[i][j], matrix))
                        return false;
                }
            }
        }
        return true;
    }

    private boolean checkDiagonal(int i, int j, int val, int[][] matrix) {
        while(i<matrix.length && j<matrix[0].length) {
            if(matrix[i][j]!=val)
                return false;
            i++;
            j++;
        }
        return true;
    }
}
