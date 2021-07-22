package problems;

public class UniquePathsAndObstacles {

    public static void main(String[] args) {
        int[][] matrix1 = {{0,0},{0,1}};
        new UniquePathsAndObstacles().uniquePathsWithObstacles(matrix1);

        int[][] matrix2 = {{1,3,1},{1,5,1},{4,2,1}};
        new UniquePathsAndObstacles().minPathSum(matrix2);
    }

    public int uniquePaths(int m, int n) {
        // if(m==1 || n==1) {
        //     return 1;
        // }
        // return uniquePaths(m-1, n) + uniquePaths(m, n-1);

        int[][] count = new int[m][n];

        for(int i=0;i<m;i++) {
            count[i][0] = 1;
        }

        for(int i=0;i<n;i++) {
            count[0][i] = 1;
        }

        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++) {
                count[i][j] = count[i-1][j] + count[i][j-1];
            }
        }
        return count[m-1][n-1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] count = new int[m][n];

        count[0][0] = (obstacleGrid[0][0]==1)?0:1;

        for(int i=1;i<m;i++) {
            if(count[i-1][0]==0) {
                count[i][0] = 0;
            } else {
                count[i][0] = (obstacleGrid[i][0]==1)?0:1;
            }
        }

        for(int i=1;i<n;i++) {
            if(count[0][i-1]==0) {
                count[0][i] = 0;
            } else {
                count[0][i] = (obstacleGrid[0][i]==1)?0:1;
            }
        }

        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++) {
                int add1 = (obstacleGrid[i-1][j]==1)?0:count[i-1][j];
                int add2 = (obstacleGrid[i][j-1]==1)?0:count[i][j-1];
                count[i][j] = (obstacleGrid[i][j]==1)?0:(add1 + add2);
            }
        }
        return count[m-1][n-1];

    }


    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] sum = new int[m][n];

        sum[0][0] = grid[0][0];
        for(int i=1;i<m;i++) {
            sum[i][0] = sum[i-1][0]+grid[i][0];
        }

        for(int i=1;i<n;i++) {
            sum[0][i] = sum[0][i-1]+grid[0][i];
        }

        for(int i=1;i<m;i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = grid[i][j]+Math.min(sum[i-1][j],sum[i][j-1]);
            }
        }

        return sum[m-1][n-1];
    }

}
