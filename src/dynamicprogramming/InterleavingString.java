package dynamicprogramming;

import java.util.Arrays;

/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 *
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<s1.length()+1;i++)
            Arrays.fill(dp[i],-1);
        if(s3.length() != s1.length() + s2.length())
            return false;
        return checkInterleave(s1, s2, s3, 0, 0, dp);
    }

    private boolean checkInterleave(String s1, String s2, String s3, int i, int j, int[][] dp) {
        if(i+j==s3.length())
            return true;

        if(dp[i][j]!=-1)
            return dp[i][j]==1;

        boolean result = false;
        if(i<s1.length() && s1.charAt(i)==s3.charAt(i+j)) {
            result = checkInterleave(s1, s2, s3, i+1, j, dp);
        }
        if(!result && j<s2.length() && s2.charAt(j)==s3.charAt(i+j)) {
            result = checkInterleave(s1, s2, s3, i, j+1, dp);
        }

        dp[i][j] = (result)?1:0;

        return result;
    }
}
