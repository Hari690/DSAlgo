package graphs;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */
public class SurroundedRegions {

    public static void main(String[] args) {
        char[][] grid = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};

        new SurroundedRegions().solve(grid);
    }

    public void solve(char[][] grid) {

        // Mark all the elements in border and connected elements with different colour as they cannot be surrounded by X
        for( int i=0; i<grid.length; i++) {
            for( int j=0; j<grid[0].length; j++) {
                if((i>=grid.length-1 || j>=grid[0].length-1 || i<1 || j<1) && grid[i][j]=='O') {
                    dfs(grid, i, j);
                }
            }
        }

        // Mark all 0s outside border to X
        for( int i=0; i<grid.length; i++) {
            for( int j=0; j<grid[0].length; j++) {
                if(grid[i][j]=='O') {
                    grid[i][j]='X';
                } else if (grid[i][j]=='P') {
                    grid[i][j]='O';
                }
            }
        }

    }

    public void dfs(char[][] grid, int i, int j) {
        if(i>=grid.length || j>=grid[0].length || i<0 || j<0 || grid[i][j]=='X' || grid[i][j]=='P') {
            return;
        }
        grid[i][j]='P';
        dfs(grid, i+1, j);
        dfs(grid, i, j+1);
        dfs(grid, i-1, j);
        dfs(grid, i, j-1);
        return;
    }
}
