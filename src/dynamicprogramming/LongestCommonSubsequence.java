package dynamicprogramming;

import java.util.Arrays;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without
 * changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 */
public class LongestCommonSubsequence {

    // use 2D dp array, when chars equal refer diagonal + 1 else refer up and down max.
    public int longestCommonSubsequence(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); ++i)
            for (int j = 0; j < s2.length(); ++j)
                if (s1.charAt(i) == s2.charAt(j)) dp[i + 1][j + 1] = 1 + dp[i][j];
                else dp[i + 1][j + 1] =  Math.max(dp[i][j + 1], dp[i + 1][j]);
        return dp[s1.length()][s2.length()];
    }

    public int longestCommonSubsequenceTopDownDp(String s1, String s2) {
        int[][] cache = new int[s1.length()][s2.length()];
        for(int i=0;i<s1.length();i++)
            Arrays.fill(cache[i],-1);
        return check(s1,s2,0,0,cache);
    }

    private int check(String s, String t, int i, int j, int[][] cache) {
        if(i==s.length() || j==t.length())
            return 0;

        if(cache[i][j]!=-1)
            return cache[i][j];

        if(s.charAt(i)==t.charAt(j))
            cache[i][j]=1+check(s,t,i+1,j+1,cache);
        else
            cache[i][j]=Math.max(check(s,t,i+1,j,cache),check(s,t,i,j+1,cache));

        return cache[i][j];
    }
}
