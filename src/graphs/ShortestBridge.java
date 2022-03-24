package graphs;

import java.util.LinkedList;
import java.util.Queue;

class Value {
    int i;
    int j;
    Value(int i, int j){
        this.i = i;
        this.j = j;
    }
}

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
class ShortestBridge {
    int[][] diffs = {{-1,0},{0,-1},{1,0},{0,1}};
    public int shortestBridge(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Value> queue = new LinkedList<>();
        outerloop:
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==1) {
                    dfs(queue, grid, i, j, visited);
                    break outerloop;
                }
            }
        }
        int distance=0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                Value value = queue.poll();

                for(int[] diff : diffs) {
                    if(value.i+diff[0]==grid.length || value.j+diff[1]==grid[0].length || value.i+diff[0]<0 || value.j+diff[1]<0 || visited[value.i+diff[0]][value.j+diff[1]])
                        continue;
                    if(grid[value.i+diff[0]][value.j+diff[1]]==1)
                        return distance;
                    queue.offer(new Value(value.i+diff[0],value.j+diff[1]));
                    visited[value.i+diff[0]][value.j+diff[1]]=true;
                }
            }
            distance++;
        }
        return -1;
    }

    private void dfs(Queue<Value> queue, int[][] grid, int i, int j, boolean[][] visited) {
        if(i==grid.length || i<0 || j<0 || j==grid[0].length || grid[i][j]==0 || visited[i][j]) {
            return;
        }
        queue.offer(new Value(i,j));
        visited[i][j]=true;
        for(int[] diff : diffs)
            dfs(queue, grid, i+diff[0], j+diff[1],visited);
    }
}
