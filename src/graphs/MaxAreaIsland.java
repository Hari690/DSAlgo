package graphs;

public class MaxAreaIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==1)
                    maxArea=Math.max(maxArea,dfs(grid, i, j));
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int i, int j) {
        if(i>=grid.length || i<0 || j>=grid[0].length || j<0 || grid[i][j]==0) {
            return 0;
        }

        int area=1;
        grid[i][j]=0;
        area+=dfs(grid, i+1, j);
        area+=dfs(grid, i, j+1);
        area+=dfs(grid, i-1, j);
        area+=dfs(grid, i, j-1);
        return area;
    }

    public static void main(String[] args) {
        MaxAreaIsland maxAreaIsland = new MaxAreaIsland();

        int[][] island = {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
        maxAreaIsland.maxAreaOfIsland(island);
    }
}
