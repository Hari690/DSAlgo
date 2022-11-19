package graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 * Notice that there could be some signs on the cells of the grid that point outside the grid.
 *
 * You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does not have to be the shortest.
 * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
 * Return the minimum cost to make the grid have at least one valid path.
 */
public class MinCostChangeArrowsMakeOnePathValidInGrid {
    int INF = (int) 1e9;

    /*
    1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
2   2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
3   3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
4   4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
     */
    int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /*
    Explanation
    Find out all reachable nodes without changing anything.
    Save all new visited nodes to a queue bfs.
    Now iterate the queue
    3.1 For each node, try changing it to all 3 other direction
    3.2 Save the new reachable and not visited nodes to the queue.
    3.3 repeat step 3

    Complexity
    Time O(NM)
    Space O(NM)
     */
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length, cost = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], INF);
        Queue<int[]> bfs = new LinkedList<>();
        dfs(grid, 0, 0, dp, cost, bfs);
        while (!bfs.isEmpty()) {
            cost++;
            for (int size = bfs.size(); size > 0; size--) {
                int[] top = bfs.poll();
                int r = top[0], c = top[1];
                for (int i = 0; i < 4; i++) dfs(grid, r + DIR[i][0], c + DIR[i][1], dp, cost, bfs);
            }
        }
        return dp[m - 1][n - 1];
    }

    void dfs(int[][] grid, int r, int c, int[][] dp, int cost, Queue<int[]> bfs) {
        int m = grid.length; int n = grid[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n || dp[r][c] != INF) return;
        dp[r][c] = cost;
        bfs.offer(new int[]{r, c}); // add to try change direction later
        // mapping direction to index
        int nextDir = grid[r][c] - 1;
        dfs(grid, r + DIR[nextDir][0], c + DIR[nextDir][1], dp, cost, bfs);
    }

    public static void main(String[] args) {
        MinCostChangeArrowsMakeOnePathValidInGrid minCostMakeOnePathValidInGrid = new MinCostChangeArrowsMakeOnePathValidInGrid();
        int[][] grid = {{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}};
        minCostMakeOnePathValidInGrid.minCost(grid);
    }
}
