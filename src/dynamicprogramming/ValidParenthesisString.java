package dynamicprogramming;

/**
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 * The following rules define a valid string:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 */
public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        return check(s, 0, 0);
    }

    private boolean check(String s, int start, int count) {
        if (count < 0) return false;

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            }
            else if (c == ')') {
                if (count <= 0) return false;
                count--;
            }
            else if (c == '*') {
                return check(s, i + 1, count + 1) || check(s, i + 1, count - 1) || check(s, i + 1, count);
            }
        }

        return count == 0;
    }

    private Boolean[][] dp;
    public boolean checkValidStringDp(String s) {
        dp = new Boolean[s.length() + 1][s.length() + 1];
        return check(s, 0, 0);
    }
    private boolean checkDp(String s, int start, int count) {
        if (count < 0) return false;
        int y = count;
        if (dp[start][y] != null) return dp[start][y];
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                if (count <= 0) return false;
                count--;
            } else if (c == '*') {
                dp[start][y] = (check(s, i + 1, count + 1) || check(s, i + 1, count - 1) || check(s, i + 1, count));
                return dp[start][y];
            }
        }
        dp[start][y] = (count == 0);
        return dp[start][y];
    }
}
