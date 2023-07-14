package graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * There is a 1-based binary matrix where 0 represents land and 1 represents water. You are given integers row and col representing the number of rows and columns in the matrix, respectively.
 * Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded with water. You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day, the cell on the rith row and cith column (1-based coordinates) will be covered with water (i.e., changed to 1).
 * You want to find the last day that it is possible to walk from the top to the bottom by only walking on land cells. You can start from any cell in the top row and end at any cell in the bottom row. You can only travel in the four cardinal directions (left, right, up, and down).
 * Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.
 * Example 1:
 * Input: row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
 * Output: 2
 * Explanation: The above image depicts how the matrix changes each day starting from day 0.
 * The last day where it is possible to cross from top to bottom is on day 2.
 *
 * Example 2:
 * Input: row = 2, col = 2, cells = [[1,1],[1,2],[2,1],[2,2]]
 * Output: 1
 * Explanation: The above image depicts how the matrix changes each day starting from day 0.
 * The last day where it is possible to cross from top to bottom is on day 1.
 *
 * Example 3:
 * Input: row = 3, col = 3, cells = [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
 * Output: 3
 * Explanation: The above image depicts how the matrix changes each day starting from day 0.
 * The last day where it is possible to cross from top to bottom is on day 3.
 */
public class LastDayWhereYouCanStillCrossLandWaterMatrix {
    public int latestDayToCross(int row, int col, int[][] cells) {
        int left = 0, right = cells.length , mid = 0;
        while(left<=right) {
            mid = left + (right-left) / 2;
            if (canWalk(cells, row, col, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left-1;
    }

    int[] DIR = new int[]{0, 1, 0, -1, 0};
    private boolean canWalk(int[][] cells, int row, int col, int dayAt) {
        int[][] grid = new int[row][col];
        for (int i = 0; i < dayAt; ++i)
            grid[cells[i][0]-1][cells[i][1]-1] = 1;
        Queue<int[]> bfs = new ArrayDeque<>();
        for (int c = 0; c < col; ++c) {
            if (grid[0][c] == 0) { // Add all valid start cells in the top row
                bfs.offer(new int[]{0, c});
                grid[0][c] = 1; // Mark as visited
            }
        }
        while (!bfs.isEmpty()) {
            int[] curr = bfs.poll();
            int r = curr[0], c  = curr[1];
            if (r == row - 1) return true; // Reached to bottom -> Return Valid
            for (int i = 0; i < 4; ++i) {
                int nr = r + DIR[i], nc = c + DIR[i+1];
                if (nr < 0 || nr == row || nc < 0 || nc == col || grid[nr][nc] == 1) continue;
                grid[nr][nc] = 1; // Mark as visited
                bfs.offer(new int[]{nr, nc});
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LastDayWhereYouCanStillCrossLandWaterMatrix solution = new LastDayWhereYouCanStillCrossLandWaterMatrix();
        int[][] matrix = {{1,1},{2,1},{1,2},{2,2}};
        solution.latestDayToCross(2,2,matrix);
    }
}
