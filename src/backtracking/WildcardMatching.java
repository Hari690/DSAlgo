package backtracking;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Example 1:
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 * Input: s = "aa", p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 *
 * Example 3:
 * Input: s = "cb", p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 */
public class WildcardMatching {

    private boolean wildcardMatch(String v, String pattern) {
        int[][] dp = new int[v.length()+1][pattern.length()];
        return backtrack(v, pattern, 0, 0, dp);
    }

    private boolean backtrack(String v, String pattern, int i, int j, int[][] dp) {
        if(i==v.length() && j==pattern.length()) {
            return true;
        }
        if(j==pattern.length()) {
            return false;
        }
        if(i==v.length() && pattern.charAt(j)!='*') {
            return false;
        }
        if(dp[i][j]!=0)
            return dp[i][j]==1;

        if(pattern.charAt(j)=='*') {
            if(i==v.length()) {
                dp[i][j] = backtrack(v, pattern, i, j+1, dp)?1:-1;
                return dp[i][j]==1;
            }
            dp[i][j] = backtrack(v, pattern, i, j+1, dp) || backtrack(v, pattern, i+1, j, dp) || backtrack(v, pattern, i+1, j+1, dp)?1:-1;
            return dp[i][j]==1;
        }
        if(pattern.charAt(j)==v.charAt(i)) {
            dp[i][j] = backtrack(v, pattern, i+1, j+1, dp)?1:-1;
            return dp[i][j]==1;
        }
        dp[i][j] = -1;
        return false;
    }

    public boolean bottomUp(String s, String p) {
        if(p.length()==0)
            return (s.length()==0);
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // populate first row for empty string as base condition.
        dp[0][0]=true;
        for(int i=1;i<=p.length();i++)
        {
            if(p.charAt(i-1)=='*')
                dp[0][i]=true;
            else
                break;
        }
        for(int i=1;i<=s.length();i++)
        {
            for(int j=1;j<=p.length();j++)
            {
                if(p.charAt(j-1)=='*')
                {
                    dp[i][j]=dp[i-1][j] || dp[i][j-1];
                }
                else if(p.charAt(j-1)==s.charAt(i-1))
                {
                    dp[i][j]=dp[i-1][j-1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }


    enum Result {
        TRUE, FALSE
    }

    Result[][] cache;

    public boolean isMatchTopDown(String text, String pattern) {
        cache = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (cache[i][j] != null) {
            return cache[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()){
            ans = i == text.length();
        } else{
            boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern) ||
                        first_match && dp(i+1, j, text, pattern));
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        cache[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    /*
        // greedy solution with idea of DFS
        // starj stores the position of last * in p
        // last_match stores the position of the previous matched char in s after a *
        // e.g.
        // s: a c d s c d
        // p: * c d
        // after the first match of the *, starj = 0 and last_match = 1
        // when we come to i = 3 and j = 3, we know that the previous match of * is actually wrong,
        // (the first branch of DFS we take is wrong)
        // then it resets j = starj + 1
        // since we already know i = last_match will give us the wrong answer
        // so this time i = last_match+1 and we try to find a longer match of *
        // then after another match we have starj = 0 and last_match = 4, which is the right solution
        // since we don't know where the right match for * ends, we need to take a guess (one branch in DFS),
        // and store the information(starj and last_match) so we can always backup to the last correct place and take another guess.
     */
    public boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*') {
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }

    public static void main(String[] args) {
        WildcardMatching wildcardMatching = new WildcardMatching();
        System.out.println(wildcardMatching.wildcardMatch("cat","tac*"));
        System.out.println(wildcardMatching.wildcardMatch("", "*"));
        System.out.println(wildcardMatching.wildcardMatch("catat", "ca*t"));
        System.out.println(wildcardMatching.wildcardMatch("cat", "ca*t"));
        System.out.println(wildcardMatching.wildcardMatch("cat", "ca*t**"));
    }
}


