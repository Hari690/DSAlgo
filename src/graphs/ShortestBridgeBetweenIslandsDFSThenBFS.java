package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
 * An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
 * You may change 0's to 1's to connect the two islands to form one island.
 * Return the smallest number of 0's you must flip to connect the two islands.
 *
 * Example 1:
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * Example 3:
 *
 * Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 */
class ShortestBridgeBetweenIslandsDFSThenBFS {
    int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    public int shortestBridge(int[][] grid) {

        boolean found = false;
        Queue<Pair<Integer,Integer>> queue = new LinkedList<>();
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==1 && !found) {
                    found = true;
                    mark(grid,i,j, queue);
                }
                if(found)
                    break;
            }

        }

        int dis = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        while(!queue.isEmpty()) {

            int size = queue.size();
            for(int i=0;i<size;i++) {
                Pair<Integer,Integer> curr = queue.poll();

                if(grid[curr.getKey()][curr.getValue()]==1) {
                    return dis-1;
                }

                for(int[] d : dir) {
                    if(curr.getKey()+d[0]>=0 && curr.getKey()+d[0]<grid.length &&
                            curr.getValue()+d[1]>=0 && curr.getValue()+d[1]<grid[0].length &&
                            !visited[curr.getKey()+d[0]][curr.getValue()+d[1]]) {
                        queue.add(new Pair(curr.getKey()+d[0],curr.getValue()+d[1]));
                        visited[curr.getKey()+d[0]][curr.getValue()+d[1]] = true;
                    }
                }
            }
            dis++;
        }

        return 0;
    }

    private void mark(int[][] grid, int i, int j, Queue<Pair<Integer,Integer>> queue) {
        if(i<0 || i==grid.length || j<0 || j==grid[0].length || grid[i][j]==0) {
            return;
        }

        grid[i][j]=0;
        queue.offer(new Pair(i,j));
        mark(grid, i+1, j, queue);
        mark(grid, i, j+1, queue);
        mark(grid, i-1, j, queue);
        mark(grid, i, j-1, queue);
    }
}
