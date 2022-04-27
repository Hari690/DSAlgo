package string;

/**
 * A valid number can be split up into these components (in order):
 *
 * A decimal number or an integer.
 * (Optional) An 'e' or 'E', followed by an integer.
 * A decimal number can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One of the following formats:
 * One or more digits, followed by a dot '.'.
 * One or more digits, followed by a dot '.', followed by one or more digits.
 * A dot '.', followed by one or more digits.
 * An integer can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One or more digits.
 * For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
 *
 * Given a string s, return true if s is a valid number.
 *
 * Example 1:
 * Input: s = "0"
 * Output: true
 *
 * Example 2:
 * Input: s = "e"
 * Output: false
 *
 * Example 3:
 * Input: s = "."
 * Output: false
 */
public class IsNumber {

    /*
        To solve this problem, we should should just make a list of the possible error conditions and then check for each one.
        The error conditions are:

        More than one exponent character ('e'/'E'), or seeing an 'e'/'E' when a number has not yet been seen.
        More than one sign, or a sign appearing after a decimal or number have been seen. This gets reset when passing an 'e'/'E'.
        More than one decimal, or a decimal appearing after an 'e'/'E' has been seen.
        Any other non-number character appearing.
        Reaching the end of S without an active number.
        To help with this process, we can set up some boolean flags for the different things of which we're keeping track (num, exp, sign, dec). We'll also need to remember to reset all flags except exp when we find an 'e'/'E', as we're starting a new integer expression.

        Time Complexity: O(N) where N is the number of characters in S
        Space Complexity: O(1)
     */
    public boolean isNumber(String S) {
        boolean num = false, exp = false, sign = false, dec = false;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c >= '0' && c <= '9') num = true ;
            else if (c == 'e' || c == 'E')
                if (exp || !num) return false;
                else {
                    exp = true;
                    sign = false;
                    num = false;
                    dec = false;
                }
            else if (c == '+' || c == '-')
                if (sign || num || dec) return false;
                else sign = true;
            else if (c == '.')
                if (dec || exp) return false;
                else dec = true;
            else return false;
        }
        return num;
    }
}
