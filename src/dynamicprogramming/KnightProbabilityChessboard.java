
class KnightProbabilityChessboard {
  private final int[][] dirs = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
  public double knightProbability(int n, int k, int row, int column) {
      double[][] dp = new double[n][n];
      dp[row][column] = 1.0;

      int m = 1;
      while(m<=k) {
          double[][] ndp = new double[n][n];
          for(int r = 0; r<n; r++) {
              for(int c = 0; c<n; c++) {
                  for(int[] d: dirs) {
                      int nr = r+d[0];
                      int nc = c+d[1];
                      // probablitiy of each move happening is 8
                      if (isValid(nr, nc, n)) ndp[r][c] += dp[nr][nc]/8.0;
                  }
              }
          }
          // update probability for next iteration. 
          dp = ndp;
          m++;
      }

      // add everything in the end
      double prob = 0.0;
      for (int r = 0; r < n; r++) {
          for (int c = 0; c < n; c++) {
              prob += dp[r][c];
          }
      }

      return prob;
  }

  private boolean isValid(int r, int c, int n) {
      return r >= 0 && r < n && c >= 0 && c < n;
  }
}
