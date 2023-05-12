package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 * Example 2:
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> output = new ArrayList<>();

        int colBegin = 0;
        int colEnd = matrix[0].length-1;
        int rowBegin = 0;
        int rowEnd = matrix.length-1;

        while( rowBegin<=rowEnd && colBegin<=colEnd) {

            for(int i=colBegin;i<=colEnd;i++) {
                output.add(matrix[rowBegin][i]);
            }
            rowBegin++;

            for(int i=rowBegin;i<=rowEnd;i++) {
                output.add(matrix[i][colEnd]);
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                for(int i=colEnd;i>=colBegin;i--) {
                    output.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }

            if (colBegin <= colEnd) {
                for(int i=rowEnd;i>=rowBegin;i--) {
                    output.add(matrix[i][colBegin]);
                }
                colBegin++;
            }
        }

        return output;
    }
}
