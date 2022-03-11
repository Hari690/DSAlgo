package graphs;

/**
 * You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An
 * island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.
 *
 * An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in
 * grid2.
 *
 * Return the number of islands in grid2 that are considered sub-islands.
 */
public class CountSubIslands {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;
        for(int i=0;i<grid1.length;i++) {
            for(int j=0;j<grid1[0].length;j++) {
                if(grid1[i][j]==1 && grid2[i][j]==1) {
                    if(dfs(grid1, grid2, i, j))
                        count++;
                }
            }
        }
        return count;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int i, int j) {
        if(i<0 || i>=grid1.length || j<0 || j>=grid1[0].length || grid2[i][j]==0)
            return true;

        if(grid1[i][j]!=1)
            return false;

        grid1[i][j]=0;
        grid2[i][j]=0;
        boolean result1 = dfs(grid1,grid2,i+1,j);
        boolean result2 = dfs(grid1,grid2,i-1,j);
        boolean result3 = dfs(grid1,grid2,i,j+1);
        boolean result4 = dfs(grid1,grid2,i,j-1);
        if(!result1 || !result2 || !result3 || !result4)
            return false;
        return true;
    }

    public static void main(String[] args) {
        int[][] grid1 = {{1,1,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,1,0,1,1}};
        int[][] grid2 = {{1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}};
        CountSubIslands countSubIslands = new CountSubIslands();
        countSubIslands.countSubIslands(grid1, grid2);
    }
}
