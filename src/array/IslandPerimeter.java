package array;

/**
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly
 * one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with
 * side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int[] indexes = {1,-1};
        int count=0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                for(int k: indexes) {
                    if(grid[i][j]==1) {
                        if(i+k>=0 && (i+k)<grid.length) {
                            count+=(grid[i+k][j]!=1)?1:0;
                        } else
                            count++;
                        if(j+k>=0 && j+k<grid[0].length) {
                            count+=(grid[i][j+k]!=1)?1:0;
                        } else
                            count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        IslandPerimeter islandPerimeter = new IslandPerimeter();
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        islandPerimeter.islandPerimeter(grid);
    }
}
