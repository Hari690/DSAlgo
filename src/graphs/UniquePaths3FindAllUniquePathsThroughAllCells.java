package graphs;

/**
 * You are given an m x n integer array grid where grid[i][j] could be:
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 *
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 *
 * Input: grid = [[0,1],[2,0]]
 * Output: 0
 * Explanation: There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 */
public class UniquePaths3FindAllUniquePathsThroughAllCells {

    public int uniquePathsIII(int[][] grid) {
        int zeroOneCount=0;
        int starti=0;
        int startj=0;

        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {

                // count 0s and 1s
                if(grid[i][j]==1 || grid[i][j]==0)
                    zeroOneCount++;

                // find start point
                if(grid[i][j]==1) {
                    starti=i;
                    startj=j;
                }
            }
        }

        // through backtracking find all paths that sum to that many zeroes. Use the grid itself for visited array
        return dfs(grid, starti,startj,0,zeroOneCount);
    }

    private int dfs(int[][] grid, int i, int j, int count, int zeroCount) {
        if(i>=grid.length || i<0 || j<0 || j>=grid[0].length || grid[i][j]==-1)
            return 0;

        if(grid[i][j]==2) {
            if(count==zeroCount)
                return 1;
            else
                return 0;
        }

        int temp=grid[i][j];
        grid[i][j]=-1;
        int no = dfs(grid,i+1,j, count+1,zeroCount)+
                dfs(grid,i,j+1, count+1, zeroCount)+
                dfs(grid,i-1,j, count+1, zeroCount)+
                dfs(grid,i,j-1, count+1, zeroCount);
        grid[i][j]=temp;
        return no;
    }

    public static void main(String[] args) {
        UniquePaths3FindAllUniquePathsThroughAllCells solution = new UniquePaths3FindAllUniquePathsThroughAllCells();

        int[][] grid = {{0,2,-1,-1},{1,-1,0,0},{-1,0,0,-1},{-1,-1,-1,-1},{-1,-1,0,-1}};
        System.out.println(solution.uniquePathsIII(grid));
    }
}
