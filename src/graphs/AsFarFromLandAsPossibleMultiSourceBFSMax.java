package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land,
 * find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 *
 * Example 1:
 * Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
 *
 * Example 2:
 * Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 */
public class AsFarFromLandAsPossibleMultiSourceBFSMax {
    int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int maxDistance(int[][] grid) {

        int[][] distance = new int[grid.length][grid[0].length];

        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==1) {
                    queue.add(new int[]{i,j});
                }
            }
        }

        if(queue.isEmpty())
            return -1;

        // We can use visited set and ignore here because in multi source DFS since each level corresponds to distance
        // so if we encounter the same cell again it is at same distance so we can skip it.
        int[][] visited = new int[grid.length][grid[0].length];
        int shortest = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                int[] head = queue.poll();
                for(int[] d : DIR) {
                    int x = head[0]+d[0];
                    int y = head[1]+d[1];
                    if(x==-1 || x==grid.length || y==-1 || y==grid[0].length || visited[x][y]!=0)
                        continue;
                    visited[x][y] = 1;
                    if(grid[x][y]!=1) {
                        distance[x][y]= distance[head[0]][head[1]]+1;
                        queue.add(new int[]{x,y});
                    }
                    shortest = Math.max(distance[x][y],shortest);
                }
            }
        }

        return shortest;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,1},{0,0,0},{1,0,1}};
        AsFarFromLandAsPossibleMultiSourceBFSMax asFarFromLandAsPossible = new AsFarFromLandAsPossibleMultiSourceBFSMax();

        System.out.println(asFarFromLandAsPossible.maxDistance(grid));
    }
}
