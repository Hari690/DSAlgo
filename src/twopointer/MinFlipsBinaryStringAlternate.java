package twopointer;

/**
 * You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:
 *
 * Type-1: Remove the character at the start of the string s and append it to the end of the string.
 * Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
 * Return the minimum number of type-2 operations you need to perform such that s becomes alternating.
 *
 * The string is called alternating if no two adjacent characters are equal.
 *
 * For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 *
 * Input: s = "111000"
 * Output: 2
 * Explanation: Use the first operation two times to make s = "100011".
 * Then, use the second operation on the third and sixth elements to make s = "101010".
 *
 */
public class MinFlipsBinaryStringAlternate {

    /*
        Idea for type 1 is to append the string twice and keep checking with 2 alt strings.
        Type 2 just compare with alt strings and get min.
     */
    public int minFlips(String s) {
        String str = s + s;
        StringBuilder alt1S = new StringBuilder("1");
        StringBuilder alt2S = new StringBuilder("0");

        for(int i=1;i<str.length();i++) {
            if(i%2==0) {
                alt1S.append("1");
                alt2S.append("0");
            } else {
                alt1S.append("0");
                alt2S.append("1");
            }
        }

        String alt1 = alt1S.toString();
        String alt2 = alt2S.toString();

        int max1=0;
        int max2=0;
        int max=Integer.MAX_VALUE;
        int left = 0;
        for(int right=0;right<str.length();right++) {
            max1+=(str.charAt(right)==alt1.charAt(right))?0:1;
            max2+=(str.charAt(right)==alt2.charAt(right))?0:1;
            if(right-left+1>s.length()) {
                max1-=(str.charAt(left)==alt1.charAt(left))?0:1;
                max2-=(str.charAt(left)==alt2.charAt(left))?0:1;
                left++;
                max = Math.min(max,Math.min(max1,max2));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MinFlipsBinaryStringAlternate solution = new MinFlipsBinaryStringAlternate();
        solution.minFlips("111000");
    }
}
