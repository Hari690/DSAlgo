package dynamicprogramming;

import java.util.Arrays;

/**
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
 *
 * A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without
 * disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * The test cases are generated so that the answer fits on a 32-bit signed integer.
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int[][] cache = new int[s.length()][t.length()];
        for(int i=0;i<s.length();i++)
            Arrays.fill(cache[i],-1);
        return check(s, 0, t, 0, cache);
    }

    private int check(String s, int i, String t, int j, int[][] cache) {
        if(j==t.length())
            return 1;

        if(i==s.length())
            return 0;

        if(cache[i][j]!=-1)
            return cache[i][j];

        if(s.charAt(i)==t.charAt(j)) {
            cache[i][j]=check(s,i+1,t,j+1,cache)+check(s,i+1,t,j,cache);
        } else {
            cache[i][j]=check(s,i+1,t,j, cache);
        }
        return cache[i][j];
    }
}
