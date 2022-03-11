package array;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern
 * in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 *
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 */
public class ZigZag {
    /*
        For all append 2*(row-1)
        For non first and last row append also j+increment-2i to account for in between elements.
     */
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numRows; i++) {
            int increment = 2 * (numRows - 1);
            for (int j = i; j < s.length(); j += increment) {
                output.append(s.charAt(j));
                if (i > 0 && i < numRows - 1 && j + increment - 2 * i < s.length()) {
                    output.append(s.charAt(j + increment - 2 * i));
                }
            }
        }

        return output.toString();
    }
}
