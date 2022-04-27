package problems;

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
 * Given a string s, return true if s is a valid number.*
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
    public boolean isNumber(String s) {
        if (s == null) return false;

        s = s.trim();
        int n = s.length();

        if (n == 0) return false;

        // flags
        int signCount = 0;
        boolean hasE = false;
        boolean hasNum = false;
        boolean hasPoint = false;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            // invalid character
            if (!isValid(c)) return false;

            // digit is always fine
            if (c >= '0' && c <= '9') hasNum = true;

            // e or E
            if (c == 'e' || c == 'E') {
                // e cannot appear twice and digits must be in front of it
                if (hasE || !hasNum) return false;
                // e cannot be the last one
                if (i == n - 1) return false;

                hasE = true;
            }

            // decimal place
            if (c == '.') {
                // . cannot appear twice and it cannot appear after e
                if (hasPoint || hasE) return false;
                // if . is the last one, digits must be in front of it, e.g. "7."
                if (i == n - 1 && !hasNum) return false;

                hasPoint = true;
            }

            // signs
            if (c == '+' || c == '-') {
                // no more than 2 signs
                if (signCount == 2) return false;
                // sign cannot be the last one
                if (i == n - 1) return false;
                // sign can appear in the middle only when e appears in front
                if (i > 0 && !hasE) return false;

                signCount++;
            }
        }

        return true;
    }

    boolean isValid(char c) {
        return c == '.' || c == '+' || c == '-' || c == 'e' || c == 'E' || c >= '0' && c <= '9';
    }
}
