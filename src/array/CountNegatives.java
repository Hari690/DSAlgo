package array;

/**
 * Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise,
 * return the number of negative numbers in grid.
 */
public class CountNegatives {
    public int countNegatives(int[][] grid) {
        int r=0, c=grid[0].length-1;
        int m=grid[0].length;
        int output=0;
        while(r<grid.length && c>=0) {
            if(grid[r][c]>=0) {
                output+=m-c-1;
                if(r<grid.length)
                    r++;
            } else {
                c--;
            }
        }
        if(r<grid.length)
            output+=(m)*(grid.length-r);
        return output;
    }
}
