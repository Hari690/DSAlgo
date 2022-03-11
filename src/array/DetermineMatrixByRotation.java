package array;

/**
 * Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree
 * increments, or false otherwise.
 *
 * Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
 * Output: true
 * Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.
 */
public class DetermineMatrixByRotation {

    /*
        Transpose and reverse row wise to get 90d rotation. Try that 4 times.
        Same algorithm as rotate matrix.
    */
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = target.length;
        if(n==1 && mat[0][0]!=target[0][0])
            return false;
        boolean result;
        for(int k=0;k<4;k++) {
            result = true;
            for(int i=0;i<mat.length;i++) {
                for(int j=i;j<mat[0].length;j++) {
                    int temp = mat[i][j];
                    mat[i][j] = mat[j][i];
                    mat[j][i]=temp;
                }
            }
            for(int i=0;i<n;i++) {
                for(int j=0;j<(n/2);j++) {
                    int temp = mat[i][j];
                    mat[i][j] = mat[i][n-j-1];
                    mat[i][n-j-1] = temp;
                }
            }
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    if(mat[i][j]!=target[i][j])
                        result = false;
                }
            }
            if(result)
                return true;
        }
        return false;
    }
}
