package dynamicprogramming;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of
 * the remaining elements.
 *
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 */
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        return topDown(s, 0, s.length()-1, dp);
    }

    private int topDown(String s, int i, int j, int[][] dp) {
        if(i>j)
            return 0;

        if(i==j)
            return 1;

        if(dp[i][j]!=0)
            return dp[i][j];

        int result;
        if(s.charAt(i)==s.charAt(j))
            result = 2+topDown(s, i+1,j-1, dp);
        else
            result = Math.max(topDown(s, i,j-1, dp), topDown(s, i+1,j, dp));
        dp[i][j] = result;
        return result;
    }
}
