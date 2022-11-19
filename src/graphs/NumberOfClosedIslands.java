package graphs;

/**
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island
 * is an island totally (all left, top, right, bottom) surrounded by 1s.
 * Return the number of closed islands.
 * Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 */
public class NumberOfClosedIslands {
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int closedIsland(int[][] grid) {
        int res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0){
                    if(dfs(grid, i, j)) res++;
                }
            }
        }

        return res;
    }

    public boolean dfs(int[][] grid, int x, int y){

        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return false;

        // return as soon as we see a 1.
        if(grid[x][y] == 1) return true;

        grid[x][y] = 1;

        boolean res = true;

        // in case of boundary since all 4 directions does not five true we return false.
        for(int[] d : dir){
            // if we encounter a boundary i.e false then all connected points also need to be marked as 1 so don't terminate.
            // DO NOT RETURN WHEN MEET WITH BOUNDARY, you need to fill all island or the unfilled island may form a new island, which is wrong
            if(!dfs(grid, x + d[0], y + d[1]))
                res = false;
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};

        NumberOfClosedIslands numberOfClosedIslands = new NumberOfClosedIslands();

        numberOfClosedIslands.closedIsland(grid);
    }
}
