package dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 * You are given a list of strings of the same length words and a string target.
 *
 * Your task is to form target using the given words under the following rules:
 *
 * target should be formed from left to right.
 * To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
 * Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every string.
 * Repeat the process until you form the string target.
 * Notice that you can use multiple characters from the same string in words provided the conditions above are met.
 * Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: words = ["acca","bbbb","caca"], target = "aba"
 * Output: 6
 * Explanation: There are 6 ways to form target.
 * "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
 * "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
 * "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
 * "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
 * "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
 * "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
 * Example 2:
 *
 * Input: words = ["abba","baab"], target = "bab"
 * Output: 4
 * Explanation: There are 4 ways to form target.
 * "bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
 * "bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
 * "bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
 * "bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")
 */
public class NumberWaysToFormTargetStringFromDictionary {

    private static final int MOD = 1000000007;

    /*
                For i & j, i will be the index of the character in string in the array and j will be index of the
                target character we are searching against.
                Then for a given index search all strings to see if any have a match and then proceed to move ahead to next index of target.
             */
    long getWords(String[] words,String target,int i,int j,long[][] dp){
        if(j == target.length())
            return 1;

        // if second clause is not there, will result in TLE.
        if(i== words[0].length() || words[0].length() - i < target.length() - j)
            return 0;

        if(dp[i][j]!=-1)
            return dp[i][j];

        long count = 0;
        for(int idx = 0; idx < words.length; idx++){
            if(words[idx].charAt(i) == target.charAt(j)){
                // case when character matches, then search for next character from next index.
                count += getWords(words, target, i + 1, j + 1, dp)%MOD;
            }
        }
        // case when no character matches, then search from next index.
        count += getWords(words, target, i + 1, j, dp)%MOD;
        dp[i][j] = count%MOD;
        return dp[i][j];
    }

    public int numWays(String[] words, String target) {
        long[][] dp = new long[words[0].length()][target.length()];
        for(int i=0;i<dp.length;i++)
            Arrays.fill(dp[i], -1);

        return (int)getWords(words, target, 0, 0, dp);
    }

    public static void main(String[] args) {
        NumberWaysToFormTargetStringFromDictionary numberWaysToFormTargetStringFromDictionary = new NumberWaysToFormTargetStringFromDictionary();
        String[] words = {"abba","baab"};
        System.out.println(numberWaysToFormTargetStringFromDictionary.numWays( words, "bab"));
    }
}
