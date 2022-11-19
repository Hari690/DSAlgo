package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.
 *
 * You are given an m x n character matrix, grid, of these different types of cells:
 *
 * '*' is your location. There is exactly one '*' cell.
 * '#' is a food cell. There may be multiple food cells.
 * 'O' is free space, and you can travel through these cells.
 * 'X' is an obstacle, and you cannot travel through these cells.
 * You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
 *
 * Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.
 *
 * Input: grid = [[“X”,”X”,”X”,”X”,”X”,”X”],[“X”,”*”,”O”,”O”,”O”,”X”],[“X”,”O”,”O”,”#”,”O”,”X”],[“X”,”X”,”X”,”X”,”X”,”X”]]
 * Output: 3
 */
public class ShortestPathToGetFood {
    public int getFood(char[][] grid) {
        int rows = grid.length, columns = grid[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int startRow = -1, startColumn = -1;
        int total = rows * columns;
        for (int i = 0; i < total; i++) {
            int row = i / columns, column = i % columns;
            if (grid[row][column] == '*') {
                startRow = row;
                startColumn = column;
                break;
            }
        }
        visited[startRow][startColumn] = true;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{startRow, startColumn});
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0], column = cell[1];
                if (grid[row][column] == '#')
                    return steps;
                for (int[] direction : directions) {
                    int newRow = row + direction[0], newColumn = column + direction[1];
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && grid[newRow][newColumn] != 'X' && !visited[newRow][newColumn]) {
                        visited[newRow][newColumn] = true;
                        queue.offer(new int[]{newRow, newColumn});
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
