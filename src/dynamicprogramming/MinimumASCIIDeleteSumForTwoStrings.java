/**
Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.

 

Example 1:

Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:

Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d] + 101[e] + 101[e] to the sum.
Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
*/
class MinimumASCIIDeleteSumForTwoStrings {

    private int[][] dp;
    
    private int remainingTotal(String s, int i) {
        int sum = 0;
        for (int j = i; j < s.length(); j++) {
            sum += s.charAt(j);
        }
        return sum;
    }
    
    private int dfs(int i, int j, String s1, String s2) {
        if (i == s1.length() && j == s2.length()) 
            return 0;
        if (i == s1.length() && j < s2.length()) 
            return remainingTotal(s2, j);
        if (j == s2.length() && i < s1.length()) 
            return remainingTotal(s1, i);
        
        if (dp[i][j] != -1) 
            return dp[i][j];
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = dfs(i + 1, j + 1, s1, s2);
        } else {
            return dp[i][j] = Math.min(s1.charAt(i) + dfs(i + 1, j, s1, s2),
                                      s2.charAt(j) + dfs(i, j + 1, s1, s2));
        }
    }
    
    public int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        dp = new int[n1][n2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, s1, s2);
    }
}
