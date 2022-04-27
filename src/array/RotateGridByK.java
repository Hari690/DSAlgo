package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
 *
 * In one shift operation:
 *
 * Element at grid[i][j] moves to grid[i][j + 1].
 * Element at grid[i][n - 1] moves to grid[i + 1][0].
 * Element at grid[m - 1][n - 1] moves to grid[0][0].
 * Return the 2D grid after applying shift operation k times.
 */
public class RotateGridByK {

    /*
        Convert to 1d array index then divide by size of grid.
        Convert back to 2d array index by dividing by column size.
        Assign for each index beginning to end.
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] result = new int[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                int index = (i*m+j+k)%(m*n);
                result[index/m][index%m] = grid[i][j];
            }
        }

        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> inner = new ArrayList<>();
            for (int j=0;j<m;j++) {
                inner.add(result[i][j]);
            }
            output.add(inner);
        }

        return output;
    }

    public static void main(String[] args) {
        RotateGridByK rotateGridByK = new RotateGridByK();
        int[][] grid = {{1},{2},{3},{4},{7},{6},{5}};
        rotateGridByK.shiftGrid(grid, 23);
    }
}
