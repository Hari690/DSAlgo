package dynamicprogramming;

/**
 * There are n uniquely-sized sticks whose lengths are integers from 1 to n. You want to arrange the sticks such that exactly k sticks are visible from the left. A stick is visible from the left if there are no longer sticks to the left of it.
 * For example, if the sticks are arranged [1,3,2,5,4], then the sticks with lengths 1, 3, and 5 are visible from the left.
 * Given n and k, return the number of such arrangements. Since the answer may be large, return it modulo 109 + 7.
 *
 * Example 1:
 * Input: n = 3, k = 2
 * Output: 3
 * Explanation: [1,3,2], [2,3,1], and [2,1,3] are the only arrangements such that exactly 2 sticks are visible.
 * The visible sticks are underlined.
 *
 * Example 2:
 * Input: n = 5, k = 5
 * Output: 1
 * Explanation: [1,2,3,4,5] is the only arrangement such that all 5 sticks are visible.
 * The visible sticks are underlined.
 *
 * Example 3:
 * Input: n = 20, k = 11
 * Output: 647427950
 * Explanation: There are 647427950 (mod 109 + 7) ways to rearrange the sticks such that exactly 11 sticks are visible.
 */
public class NumberOfWaysRearrangeSticksBuildingsBlocksWithKVisible {
    /*
        We select the tallest of the n sticks. There's only one such stick and this stick will finally be visible from the left.
        And so we are left with the sub-problem (n - 1, k - 1).
        We select a stick that's not the tallest one. We can do this by selecting any of the n - 1 sticks that are not the tallest one.
        So there are n - 1 sub-problems and each of them is (n - 1, k). This stick we selected will not be visible from the left,
        so we still gotta select k sticks from the remaining pile that will be visible, that's why the sub-problem has k, and not k - 1.
     */

    int mod = 1000000007;
    public int rearrangeSticks(int n, int k) {
        int[][] dp=new int[n+1][k+1];
        return helper(n,k,dp);
    }

    public int helper(int n,int k,int[][] dp){
        if(n==k){
            return 1;
        }
        if(k==0){
            return 0;
        }
        if(dp[n][k]==0){
            dp[n][k]=(int)((1L * helper(n - 1, k - 1,dp) + 1L * helper(n - 1, k,dp) * (n - 1)) % mod);

        }
        return dp[n][k];
    }
}
