package dynamicprogramming;

/**
 * Given a string s. In one step you can insert any character at any index of the string.
 * Return the minimum number of steps to make s palindrome.
 * A Palindrome String is one that reads the same backward as well as forward.
 *
 * Example 1:
 *
 * Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we do not need any insertions.
 * Example 2:
 *
 * Input: s = "mbadm"
 * Output: 2
 * Explanation: String can be "mbdadbm" or "mdbabdm".
 * Example 3:
 *
 * Input: s = "leetcode"
 * Output: 5
 * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 */
public class MinStepsMakeStringPalindrome {

    /*
        Boils down to finding the part of string that's not part of longest palindromic subsequence.
     */
    public int minInsertions(String s) {
        return s.length()-longestPalindromeSubseq(s);
    }

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
