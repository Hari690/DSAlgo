package graphs;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 *
 * The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally
 * adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time.
 * Of course, you must stay within the boundaries of the grid during your swim.
 *
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 */
public class SwimRisingWater {
    class Value{
        int t;
        int x;
        int y;
        Value(int t,int x, int y) {
            this.t = t;
            this.x = x;
            this.y = y;
        }
    }

    public int swimInWater(int[][] grid) {
        PriorityQueue<Value> minHeap = new PriorityQueue<>((s1,s2)->s1.t-s2.t);
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        minHeap.add(new Value(grid[0][0],0,0));
        visited[0][0] = true;

        while(!minHeap.isEmpty()) {
            Value val = minHeap.poll();
            if(grid[val.x][val.y]==grid[grid.length-1][grid[0].length-1])
                return val.t;
            int x=val.x;
            int y=val.y;
            for(int[] dir : dirs) {
                if(x+dir[0]>=0 && x+dir[0]<grid.length &&
                    y+dir[1]>=0 && y+dir[1]<grid[0].length &&
                    !visited[x+dir[0]][y+dir[1]]) {
                    visited[x+dir[0]][y+dir[1]]=true;
                    minHeap.add(new Value(Math.max(grid[x+dir[0]][y+dir[1]],val.t),x+dir[0],y+dir[1]));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,2},{1,3}};
        SwimRisingWater swimRisingWater = new SwimRisingWater();
        swimRisingWater.swimInWater(grid);
    }
}
