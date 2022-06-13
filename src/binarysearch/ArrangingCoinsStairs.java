package binarysearch;

/**
 * You have n coins and you want to build a staircase with these coins. The staircase consists of k rows where the ith row has exactly i coins. The last row of the staircase may be incomplete.
 * Given the integer n, return the number of complete rows of the staircase you will build.
 * Input: n = 5
 * Output: 2
 * Explanation: Because the 3rd row is incomplete, we return 2.
 *
 * Input: n = 8
 * Output: 3
 * Explanation: Because the 4th row is incomplete, we return 3.
 */
public class ArrangingCoinsStairs {
    public int arrangeCoins(int n) {
        int l=1;
        int r=n;
        int result=0;
        while(l<=r) {
            int m = l+(r-l)/2;
            int sum = m * (m+1) / 2;
            if(sum<=n) {
                l = m+1;
                result = Math.max(result, m);
            } else {
                r = m-1;
            }
        }
        return result;
    }
}
