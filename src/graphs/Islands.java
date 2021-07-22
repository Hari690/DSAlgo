package graphs;

public class Islands {

    public static void main(String[] args) {

        char[][] grid = new char[0][];

        Islands.numIslands(grid);

    }

    public static int numIslands(char[][] grid) {
        int numIslands = 0;

        for( int i=0; i<grid.length; i++) {
            for( int j=0; j<grid[0].length; j++) {
                if(grid[i][j]=='1') {
                    numIslands += dfs(grid, i, j);
                }
            }
        }

        return numIslands;
    }

    public static int dfs(char[][] grid, int i, int j) {
        if(i>grid.length || j>grid[0].length || i<0 || j<0 || grid[i][j]=='0') {
            return 0;
        }
        grid[i][j]='0';
        dfs(grid, i+1, j);
        dfs(grid, i, j+1);
        dfs(grid, i-1, j);
        dfs(grid, i, j-1);
        return 1;
    }
}
