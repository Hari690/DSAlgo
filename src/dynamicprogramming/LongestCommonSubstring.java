package dynamicprogramming;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without
 * changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 */
public class LongestCommonSubstring {

    // use 2D dp array, when chars equal refer diagonal + 1 else 0.
    public int longestCommonSubstring(String s1, String s2) {
        int maxSubstringLength = 0;
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); ++i)
            for (int j = 0; j < s2.length(); ++j)
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                    maxSubstringLength = Math.max(maxSubstringLength, dp[i + 1][j + 1]);
                }
                else
                    dp[i + 1][j + 1] =  0;
        return maxSubstringLength;

    }
}
