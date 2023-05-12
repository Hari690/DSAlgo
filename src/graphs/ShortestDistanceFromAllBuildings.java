package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 * Note: There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 * The question requires BFS to solve, classic, generally need queue.
 */
public class ShortestDistanceFromAllBuildings {
    class P {
        int x;
        int y;
        int step;

        public P(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        int[][] counter = new int[m][n];
        int numBuildings = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    ++numBuildings;
                    bfs(grid, dist, counter, i, j, m, n);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        boolean found = false;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dist[i][j] != 0 && counter[i][j] == numBuildings && grid[i][j] == 0) {
                    min = Math.min(min, dist[i][j]);
                    found = true;
                }
            }
        }

        return found ? min : -1;
    }

    private void bfs(int[][] grid, int[][] dist, int[][] counter, int x, int y, int m, int n) {
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<P> queue = new LinkedList<P>();
        queue.add(new P(x, y, 0));
        while (!queue.isEmpty()) {
            P p = queue.poll();
            for (int[] dir : dirs) {
                int i = p.x + dir[0];
                int j = p.y + dir[1];
                if (i >= 0 && i < m && j >= 0 && j < n && !visited[i][j] && grid[i][j] == 0) {
                    queue.add(new P(i, j, p.step + 1));
                    dist[i][j] += p.step + 1;
                    counter[i][j] += 1;
                    visited[i][j] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        ShortestDistanceFromAllBuildings s = new ShortestDistanceFromAllBuildings();
        System.out.println(s.shortestDistance(grid));
    }
}