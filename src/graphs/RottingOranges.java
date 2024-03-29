package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n grid where each cell can have one of three values:
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 *
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 *
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 */
public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        // keep a count of fresh oranges
        Queue<int[]> rotten = new LinkedList();
        int r = grid.length, c = grid[0].length, fresh = 0, t = 0;
        for(int i = 0; i < r; ++i){
            for(int j = 0; j < c; ++j){
                if(grid[i][j] == 2) rotten.add(new int[]{i, j});
                else if(grid[i][j] == 1) fresh++;
            }
        }

        // keep decreasing fresh orange count
        while(!rotten.isEmpty()){
            int num = rotten.size();
            for(int i = 0; i < num; ++i){
                int x = rotten.peek()[0], y = rotten.peek()[1];
                rotten.remove();
                if(x > 0 && grid[x-1][y] == 1){ grid[x-1][y] = 2; fresh--; rotten.add(new int[]{x-1, y});};
                if(y > 0 && grid[x][y-1] == 1){ grid[x][y-1] = 2; fresh--; rotten.add(new int[]{x, y-1});};
                if(x < r-1 && grid[x+1][y] == 1){ grid[x+1][y] = 2; fresh--; rotten.add(new int[]{x+1, y});};
                if(y < c-1 && grid[x][y+1] == 1){ grid[x][y+1] = 2; fresh--; rotten.add(new int[]{x, y+1});};
            }
            if(!rotten.isEmpty()) t++;
        }
        return (fresh == 0) ? t : -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,1},{0,1,2}};
        //int[][] grid = {{0,2}};
        //221,211,012
        //222,221,022
        System.out.println(new RottingOranges().orangesRotting(grid));
    }
}
