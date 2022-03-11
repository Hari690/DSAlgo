package array;

/**
 * An integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.
 * Given an integer n, return the largest number that is less than or equal to n with monotone increasing digits.
 *
 * Example 1:
 * Input: n = 10
 * Output: 9
 *
 * Example 2:
 * Input: n = 1234
 * Output: 1234
 *
 * Example 3:
 * Input: n = 332
 * Output: 299
 */
public class SmallerElementWithIncreasingDigits {
    /*
        Intuition is to start backwards and when there's a increase reduce previous value by 1 to bring it under current n.
        Afterwards mark the last place change was done and pad 9s after it to get largest value.
        For cases like 10 it will become 00 -> 09 so trailing 0s will be removed when we convert to integer.
     */
    public int monotoneIncreasingDigits(int n) {
        char[] chars = String.valueOf(n).toCharArray();

        int marker=chars.length;
        for(int i=chars.length-1;i>0;i--) {
            if(chars[i]<chars[i-1]) {
                chars[i-1]=(char)(chars[i-1]-1);
                marker=i;
            }
        }

        for(int i=marker;i<chars.length;i++)
            chars[i]='9';

        return Integer.parseInt(new String(chars));
    }

    public static void main(String[] args) {
        SmallerElementWithIncreasingDigits smallerElementWithIncreasingDigits = new SmallerElementWithIncreasingDigits();
        smallerElementWithIncreasingDigits.monotoneIncreasingDigits(10);
    }
}
