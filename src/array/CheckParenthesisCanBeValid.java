package array;

/**
 * A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:
 *
 * It is ().
 * It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
 * It can be written as (A), where A is a valid parentheses string.
 * You are given a parentheses string s and a string locked, both of length n. locked is a binary string consisting only of '0's and '1's. For each index i of locked,
 *
 * If locked[i] is '1', you cannot change s[i].
 * But if locked[i] is '0', you can change s[i] to either '(' or ')'.
 * Return true if you can make s a valid parentheses string. Otherwise, return false.
 *
 * Input: s = "))()))", locked = "010100"
 * Output: true
 * Explanation: locked[1] == '1' and locked[3] == '1', so we cannot change s[1] or s[3].
 * We change s[0] and s[4] to '(' while leaving s[2] and s[5] unchanged to make s valid.
 *
 * Input: s = "()()", locked = "0000"
 * Output: true
 * Explanation: We do not need to make any changes because s is already valid.
 *
 * Input: s = ")", locked = "0"
 * Output: false
 * Explanation: locked permits us to change s[0].
 * Changing s[0] to either '(' or ')' will not make s valid.
 */
public class CheckParenthesisCanBeValid {

    /*
        Check left to right and right to left and see if wild characters i.e eg closed without open before it
        is more then return false and then do same in reverse.
        Imp : also check the size of array is odd then false.
     */
    public boolean canBeValid(String s, String locked) {

        if(s.length()%2!=0)
            return false;

        int open=0,closed=0,wild=0;
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)=='(' || locked.charAt(i)=='0')
                open++;
            else
                wild++;

            if (wild>open)
                return false;
        }

        wild=0;
        for(int i=s.length()-1;i>=0;i--) {
            if(s.charAt(i)==')' || locked.charAt(i)=='0')
                closed++;
            else
                wild++;

            if(wild>closed)
                return false;
        }

        return true;
    }
}
