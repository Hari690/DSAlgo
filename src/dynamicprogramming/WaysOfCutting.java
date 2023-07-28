package dynamicprogramming;

/**
 *Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts.
 * For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.
 * Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.
 * Example 1:
 * Input: pizza = ["A..","AAA","..."], k = 3
 * Output: 3
 * Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
 *
 * Example 2:
 * Input: pizza = ["A..","AA.","..."], k = 3
 * Output: 1
 *
 * Example 3:
 * Input: pizza = ["A..","A..","..."], k = 1
 * Output: 1
 *
 */
class WaysOfCuttingPizza3DimesionalDP {
    final int MOD = (int) 1e9+7;

    /*
    This solution uses a significant amount of preprocessing; the actual DP is not so bad once the preprocessing is done.
    The idea is to first convert the input to a binary matrix where an entry is 1 if and only if the input has an 'A' in that position.
    Then, we compute a "prefix sum" matrix, which will allow us to fetch the sum for any sub-matrix of the input matrix.
    For the actual DP, if we have made k cuts, we just require that the last piece has an apple (i.e. sums to at least 1).
    Otherwise, we simply try all cuts horizontally, then vertically, and for each attempt we only seek the answer with 1 fewer cuts needed
    for the lower- or right-piece if the upper- or left-piece has at least 1 apple.
    Addendum: Notice we always have the bottom-right point fixed as (m-1,n-1);
    I didn't notice during the contest, but taking this into account easily reduces the DP to O(mn(m+n)k) time complexity.
    Also we don't even need to preprocess the initial matrix into a binary 2D matrix explicitly; we would only use it for computing the sums matrix,
    so we can just check the corresponding character in the input when computing the sums matrix instead.
     */
    public int ways(String[] pizza, int k) {
        int m, n;
        m = pizza.length;
        n = pizza[0].length();
        int[][] sums = new int[m+1][n+1];

        // prefix sums
        for (int i = m-1; i >= 0; i--)
            for (int j = n-1; j >= 0; j--)
                sums[i][j] = (pizza[i].charAt(j) == 'A' ? 1 : 0)
                        + sums[i+1][j] + sums[i][j+1] - sums[i+1][j+1];
        Integer[][][] dp = new Integer[m][n][k+1];
        return wayRec(0, 0, 1, sums, dp, m, n, k);
    }

    private int wayRec(int i, int j, int cuts, int[][] sums, Integer[][][] dp, int m, int n,int k) {
        if (dp[i][j][cuts] != null) return dp[i][j][cuts];
        if (cuts == k) return dp[i][j][cuts] = (sums[i][j] > 0 ? 1 : 0);
        int ret = 0;
        for (int r = i; r < m-1; r++)
            if (sums[i][j] - sums[r+1][j] > 0) ret = (ret + wayRec(r+1, j, cuts+1, sums, dp, m, n, k)) % MOD;
        for (int c = j; c < n-1; c++)
            if (sums[i][j] - sums[i][c+1] > 0) ret = (ret + wayRec(i, c+1, cuts+1, sums, dp, m, n, k)) % MOD;
        return dp[i][j][cuts] = ret;
    }
}
