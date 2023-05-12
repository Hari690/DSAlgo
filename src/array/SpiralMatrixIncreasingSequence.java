package array;

/**
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 *
 * Example 1:
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 *
 * Example 2:
 * Input: n = 1
 * Output: [[1]]
 */
public class SpiralMatrixIncreasingSequence {
    public int[][] generateMatrix(int n) {
        int[][] output = new int[n][n];

        int colBegin = 0;
        int colEnd = n-1;
        int rowBegin = 0;
        int rowEnd = n-1;
        int value = 1;

        while( rowBegin<=rowEnd && colBegin<=colEnd) {

            for(int i=colBegin;i<=colEnd;i++) {
                output[rowBegin][i]=value++;
            }
            rowBegin++;

            for(int i=rowBegin;i<=rowEnd;i++) {
                output[i][colEnd]=value++;
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                for(int i=colEnd;i>=colBegin;i--) {
                    output[rowEnd][i]=value++;
                }
                rowEnd--;
            }

            if (colBegin <= colEnd) {
                for(int i=rowEnd;i>=rowBegin;i--) {
                    output[i][colBegin]=value++;
                }
                colBegin++;
            }
        }

        return output;
    }
}
