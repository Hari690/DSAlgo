package array;

/**
 * You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing
 * brackets ']'.
 *
 * A string is called balanced if and only if:
 *
 * It is the empty string, or
 * It can be written as AB, where both A and B are balanced strings, or
 * It can be written as [C], where C is a balanced string.
 * You may swap the brackets at any two indices any number of times.
 *
 * Return the minimum number of swaps to make s balanced.
 *
 *
 * Example 1:
 *
 * Input: s = "][]["
 * Output: 1
 * Explanation: You can make the string balanced by swapping index 0 with index 3.
 * The resulting string is "[[]]".
 */
public class MinSwaps {
    public int minSwaps(String s) {
        int extraClose = 0;
        for(char c : s.toCharArray()) {
            if(c=='[')
                extraClose++;
            if(c==']')
                extraClose--;
            extraClose = Math.max(extraClose,Math.abs(extraClose));
        }
        return (int)Math.ceil((double)extraClose/2);
    }
}
