package deque;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given NxN matrix with integer numbers.
 * Find the greatest number for every 3x3 matrix.
 *
 * update example
 *
 * example.1
 * [[1,2,3],
 * [4,5,6],
 * [7,8,9]]
 *
 * ans:
 * [9]
 *
 * example.2
 * [[1,2,3,6],
 * [5,2,4,1],
 * [3,4,2,3],
 * [8,2,1,4]]
 *
 * ans:
 * [[5,6],
 * [8,4]]
 */
public class Max3X3Matrix {
    // pseudo
    public int[][] findMax(int[][] grid) {
        Deque<Integer> deque = new LinkedList<>();

        int[][] result = new int[grid.length-2][grid[0].length-2];
        // find max row wise for k sized window
        for(int[] row : grid) {

        }

        // find max for k sized window column wise
        return result;
    }
}
