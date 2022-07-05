package dynamicprogramming;

import java.util.Arrays;

/**
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 * In one step, you can delete exactly one character in either string.
 *
 * Example 1:
 * Input: word1 = "sea", word2 = "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * Example 2:
 * Input: word1 = "leetcode", word2 = "etco"
 * Output: 4
 *
 */
public class DeleteOperationTwoStrings {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for(int i=0;i<word1.length();i++)
            Arrays.fill(dp[i], -1);
        return findMin(0, 0, word1, word2, dp);
    }

    private int findMin(int i, int j, String word1, String word2, int[][] dp) {
        if(i==word1.length() && j==word2.length())
            return 0;

        if(i==word1.length())
            return word2.length()-j;

        if(j==word2.length())
            return word1.length()-i;

        if(dp[i][j]!=-1)
            return dp[i][j];

        if(word1.charAt(i)==word2.charAt(j)) {
            dp[i][j] = findMin(i+1, j+1, word1, word2, dp);
            return dp[i][j];
        }

        dp[i][j] = Math.min(2 + findMin(i+1, j+1, word1, word2, dp),
                Math.min(1 + findMin(i, j+1, word1, word2, dp), 1 + findMin(i+1, j, word1, word2, dp)));
        return dp[i][j];
    }

    public static void main(String[] args) {
        DeleteOperationTwoStrings deleteOperationTwoStrings = new DeleteOperationTwoStrings();

        deleteOperationTwoStrings.minDistance("ma", "ka");
    }
}
