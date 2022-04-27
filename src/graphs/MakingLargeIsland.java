package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 * Return the size of the largest island in grid after applying this operation.
 * An island is a 4-directionally connected group of 1s.
 *
 * Example 1:
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 */
public class MakingLargeIsland {

    /*
        Intuition - Mark different islands with different colour.
        Keep checking for 0's and count contribution of different islands surrounding it.
     */
    int[][] diffs = {{1,0},{0,1},{-1,0},{0,-1}};
    public int largestIslandBruteForce(int[][] grid) {
        int max=0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==0) {
                    grid[i][j]=1;
                    max = Math.max(max,dfs(grid, i, j, new boolean[grid.length][grid[0].length]));
                    grid[i][j]=0;
                }
            }
        }
        return (max==0)?grid.length*grid[0].length:max;
    }

    private int dfs(int[][] grid, int i , int j, boolean[][] visited) {
        if(i<0 || j<0 || i==grid.length || j==grid[0].length || grid[i][j]==0 || visited[i][j])
            return 0;

        visited[i][j] = true;
        int total = 1;
        for(int[] diff : diffs) {
            total+=dfs(grid, i+diff[0], j+diff[1], visited);
        }
        return total;
    }

    public int largestIslandMap(int[][] grid) {
        Map<Integer, Integer> regionsArea = new HashMap<>();

        int n = grid.length;
        int region = 2;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    int area = floodFill(grid, i, j, region);
                    regionsArea.put(region, area);
                    region++;
                }
            }
        }

        // default value.
        regionsArea.put(0,0);
        int max = regionsArea.getOrDefault(2,0);
        for(int r=0;r<n;r++){
            for(int c=0;c<n;c++){
                if(grid[r][c]==0){
                    // get contribution from each of it's neighbours.
                    Set<Integer> neighbors = new HashSet<>();
                    neighbors.add(r>0?grid[r-1][c]:0);
                    neighbors.add(c>0?grid[r][c-1]:0);
                    neighbors.add(r<n-1?grid[r+1][c]:0);
                    neighbors.add(c<n-1?grid[r][c+1]:0);
                    int area = 1;
                    for(int neighbor: neighbors){
                        area+=regionsArea.get(neighbor);
                    }
                    if(area>max){
                        max = area;
                    }
                }
            }
        }

        return max;
    }

    public int floodFill(int[][] grid, int r, int c, int region) {
        int n = grid.length;
        if(r<0||r>=n||c<0||c>=n||grid[r][c]!=1){
            return 0;
        }

        grid[r][c] = region;

        int sum = 1;
        sum+=floodFill(grid, r, c+1, region);
        sum+=floodFill(grid, r+1, c, region);
        sum+=floodFill(grid, r, c-1, region);
        sum+=floodFill(grid, r-1, c, region);

        return sum;
    }
}
