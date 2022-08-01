package matrix;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a matrix and a target, return the number of non-empty submatrices that sum to target.
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
 *
 * Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * Output: 4
 * Explanation: The four 1x1 submatrices that only contain 0.
 *
 * Example 2:
 * Input: matrix = [[1,-1],[-1,1]], target = 0
 * Output: 5
 * Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
 *
 * Example 3:
 * Input: matrix = [[904]], target = 0
 * Output: 0
 */
public class NumberSubmatricesThatSumTarget {

    /* idea here is to maintain column wise sum and then iterate between two sets of columns (c2(guided by c1)-n).
        then create submatrixes by iterating the rows.
        Reduce c1-1 columns to find the sums within the submatrix and then apply
        continuous subset sum equals k method.
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {


        int m=matrix.length;
        int n=matrix[0].length;
        for(int i=0;i<m;i++) {
            for(int j=1;j<n;j++) {
                matrix[i][j]+=matrix[i][j-1];
            }
        }

        int total=0;
        for(int c1=0;c1<n;c1++) {
            for(int c2=c1;c2<n;c2++) {
                int sum = 0;
                Map<Integer,Integer> map = new HashMap<>();
                map.put(0,1);
                for(int r=0;r<n;r++) {
                    sum+=matrix[r][c2]-((c1>0)?matrix[r][c1-1]:0);
                    if(map.containsKey(sum-target))
                        total+=map.get(sum-target);
                    map.put(sum, map.getOrDefault(sum,0)+1);
                }
            }
        }

        return total;
    }

    public static void main(String[] args) {
        NumberSubmatricesThatSumTarget numberSubmatricesThatSumTarget = new NumberSubmatricesThatSumTarget();

        int[][] matrix = {{0,1,0},{1,1,1},{0,1,0}};
        numberSubmatricesThatSumTarget.numSubmatrixSumTarget(matrix, 0);
    }
}