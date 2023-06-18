package array;

/**
 * Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.
 * Example 1:
 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negatives number in the matrix.
 *
 * Example 2:
 * Input: grid = [[3,2],[1,0]]
 * Output: 0
 */
public class CountNegativeInSortedMatrix {

    /*
        Explanation why time complexity is O(M+N)
        1: All negative in first row -> In that case how many passes u make? it will be equal to column length(grid[0].length)
        2: All +tive in first row -> In that case how many passes u make? it will be equal to row length(grid.length)
        3: Worst case, you have something like
        ++++-
        +++--
        ++---
        +----
     */
    public int countNegatives(int[][] grid) {
        int count = 0;
        int j=0;
        for(int i=grid.length-1;i>=0;i--) {
            while(j<grid[0].length) {
                if(grid[i][j]<0) {
                    count+=grid[0].length-j;
                    break;
                }
                j++;
            }
        }

        return count;
    }
}
