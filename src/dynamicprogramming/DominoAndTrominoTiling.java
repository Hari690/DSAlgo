package dynamicprogramming;

/**
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
 * Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.
 *
 * In a tiling, every square must be covered by a tile.
 * Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.
 * Input: n = 3
 * Output: 5
 * Explanation: The five different ways are show above.
 */
public class DominoAndTrominoTiling {

    /*
        dp[1] = {|}
        dp[2] = {||}
        dp[3] = {|||,|=,=|,|=|,|=|}
        dp[4] also we have 2 tromino's contributing
        For n-1 & n-2 we have contribution only from domino's and n-3 we have 2 tromino's being added for each of remaining.
        dp[n]=dp[n-1]+dp[n-2]+ 2*(dp[n-3]+...+d[0])
        =dp[n-1]+dp[n-2]+dp[n-3]+dp[n-3]+2*(dp[n-4]+...+d[0])
        =dp[n-1]+dp[n-3]+(dp[n-2]+dp[n-3]+2*(dp[n-4]+...+d[0]))
        =dp[n-1]+dp[n-3]+dp[n-1]
        =2*dp[n-1]+dp[n-3]
     */
    public int numTilings(int n) {
        int dp[] = new int[n+4];

        int mod = (int)(Math.pow(10,9)+7);
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;

        for(int i=4;i<=n;i++){
            dp[i] = ((2*dp[i-1])%mod)+dp[i-3];
            dp[i] %= mod;
        }
        return dp[n];
    }
}
